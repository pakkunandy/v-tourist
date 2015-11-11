using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Http;
using System.Web.Http.Description;
using WebAPI.Models;

namespace WebAPI.Controllers
{
    public class ImagesController : ApiController
    {
        private WebAPIContext db = new WebAPIContext();

        // GET: api/Images
        public IQueryable<ImageDetailDTO> GetImages()
        {
            var images = from i in db.Images
                         select new ImageDetailDTO()
                         {
                             ImgID = i.ImgID,
                             URL = i.URL,
                             Place_name = i.Place.Name
                         };
            return images;
        }

        // GET: api/Images/1
        [ResponseType(typeof(ImageDetailDTO))]
        public async Task<IHttpActionResult> GetImage(int id)
        {
            var image = await db.Images.Include(i => i.Place).Select(i =>
            new ImageDetailDTO()
            {
                ImgID = i.ImgID,
                URL = i.URL,
                Place_name = i.Place.Name
            }).SingleOrDefaultAsync(i => i.ImgID == id);
            if (image == null)
            {
                return NotFound();
            }

            return Ok(image);
        }

        // PUT: api/Images/5
        [ResponseType(typeof(void))]
        public async Task<IHttpActionResult> PutImage(int id, Image image)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != image.ImgID)
            {
                return BadRequest();
            }

            db.Entry(image).State = EntityState.Modified;

            try
            {
                await db.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ImageExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return StatusCode(HttpStatusCode.NoContent);
        }

        // POST: api/Images
        [ResponseType(typeof(ImageDetailDTO))]
        public async Task<IHttpActionResult> PostImage(Image image)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Images.Add(image);
            await db.SaveChangesAsync();

            db.Entry(image).Reference(i => i.Place).Load();

            var dto = new ImageDetailDTO()
            {
                ImgID = image.ImgID,
                URL = image.URL,
                Place_name = image.Place.Name
            };

            return CreatedAtRoute("DefaultApi", new { id = image.ImgID }, dto);
        }

        // DELETE: api/Images/5
        [ResponseType(typeof(Image))]
        public async Task<IHttpActionResult> DeleteImage(int id)
        {
            Image image = await db.Images.FindAsync(id);
            if (image == null)
            {
                return NotFound();
            }

            db.Images.Remove(image);
            await db.SaveChangesAsync();

            return Ok(image);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool ImageExists(int id)
        {
            return db.Images.Count(e => e.ImgID == id) > 0;
        }
    }
}