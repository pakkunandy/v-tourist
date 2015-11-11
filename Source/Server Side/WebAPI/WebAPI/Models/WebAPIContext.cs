using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace WebAPI.Models
{
    public class WebAPIContext : DbContext
    {
        // You can add custom code to this file. Changes will not be overwritten.
        // 
        // If you want Entity Framework to drop and regenerate your database
        // automatically whenever you change your model schema, please use data migrations.
        // For more information refer to the documentation:
        // http://msdn.microsoft.com/en-us/data/jj591621.aspx
    
        public WebAPIContext() : base("name=WebAPIContext")
        {
        }

        public System.Data.Entity.DbSet<WebAPI.Models.Type> Types { get; set; }

        public System.Data.Entity.DbSet<WebAPI.Models.City> Cities { get; set; }

        public System.Data.Entity.DbSet<WebAPI.Models.District> Districts { get; set; }

        public System.Data.Entity.DbSet<WebAPI.Models.Ward> Wards { get; set; }

        public System.Data.Entity.DbSet<WebAPI.Models.Place> Places { get; set; }

        public System.Data.Entity.DbSet<WebAPI.Models.Image> Images { get; set; }
    }
}
