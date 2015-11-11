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
    public class WardsController : ApiController
    {
        private WebAPIContext db = new WebAPIContext();

        // GET: api/Wards
        public IQueryable<Ward> GetWards()
        {
            return db.Wards;
        }

        // GET: api/Wards/5
        [ResponseType(typeof(Ward))]
        public async Task<IHttpActionResult> GetWard(int id)
        {
            Ward ward = await db.Wards.FindAsync(id);
            if (ward == null)
            {
                return NotFound();
            }

            return Ok(ward);
        }

        // PUT: api/Wards/5
        [ResponseType(typeof(void))]
        public async Task<IHttpActionResult> PutWard(int id, Ward ward)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != ward.ward_id)
            {
                return BadRequest();
            }

            db.Entry(ward).State = EntityState.Modified;

            try
            {
                await db.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!WardExists(id))
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

        // POST: api/Wards
        [ResponseType(typeof(Ward))]
        public async Task<IHttpActionResult> PostWard(Ward ward)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Wards.Add(ward);
            await db.SaveChangesAsync();

            return CreatedAtRoute("DefaultApi", new { id = ward.ward_id }, ward);
        }

        // DELETE: api/Wards/5
        [ResponseType(typeof(Ward))]
        public async Task<IHttpActionResult> DeleteWard(int id)
        {
            Ward ward = await db.Wards.FindAsync(id);
            if (ward == null)
            {
                return NotFound();
            }

            db.Wards.Remove(ward);
            await db.SaveChangesAsync();

            return Ok(ward);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool WardExists(int id)
        {
            return db.Wards.Count(e => e.ward_id == id) > 0;
        }
    }
}