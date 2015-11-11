using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;


namespace WebAPI.Models
{
    public class Place
    {
        [Key]
        public int id { get; set; }
        [Required]
        public string Name { get; set; }
        public string Description { get; set; }
        public string Address { get; set; }
        public float Latitude { get; set; }
        public float Longitude { get; set; }
        public string Phone { get; set; }

        //Foreign key
        public int City { get; set; }
        public int District { get; set; }
        public int Ward { get; set; }
        public int type { get; set; }
        //Navigation property
        
        public Type Type { get; set; }
    }
}