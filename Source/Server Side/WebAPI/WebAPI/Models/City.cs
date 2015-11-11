using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.ComponentModel.DataAnnotations;

namespace WebAPI.Models
{
    public class City
    {
        [Key]
        public int city_id { get; set; }
        [Required]
        public string name { get; set; }
        public string atribute_name { get; set; }
    }
}