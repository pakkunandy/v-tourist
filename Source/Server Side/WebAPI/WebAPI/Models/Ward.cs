using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace WebAPI.Models
{
    public class Ward
    {
        [Key]
        public int ward_id { get; set; }
        [Required]
        public string name { get; set; }

        //Foreign key
        public int city_id { get; set; }
        public int district_id { get; set; }
        //Navigation property
        public District District { get; set; }

    }
}