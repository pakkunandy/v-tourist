using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebAPI.Models
{
    public class ImageDetailDTO
    {
        public int ImgID { get; set; }
        public string URL { get; set; }
        public string Place_name { get; set; }
    }
}