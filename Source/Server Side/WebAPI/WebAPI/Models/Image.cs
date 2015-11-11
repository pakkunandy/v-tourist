using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace WebAPI.Models
{
    public class Image
    {
        [Key]
        public int ImgID { get; set; }
        [Required]
        public string URL { get; set; }

        //Foregin key
        public int PlaceID { get; set; }

        //Navigation property
        public Place Place { get; set; }

    }
}