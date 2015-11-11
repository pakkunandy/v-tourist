using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.ComponentModel.DataAnnotations;
namespace WebAPI.Models
{
    public class Type
    {
        [Key]
        public int Type_id { get; set; }
        [Required]
        public string name { get; set; }
        public string description { get; set; }
    }
}