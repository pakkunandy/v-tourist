namespace WebAPI.Migrations
{
    using System;
    using System.Data.Entity;
    using System.Data.Entity.Migrations;
    using System.Linq;
    using WebAPI.Models;

    internal sealed class Configuration : DbMigrationsConfiguration<WebAPI.Models.WebAPIContext>
    {
        public Configuration()
        {
            AutomaticMigrationsEnabled = false;
        }

        protected override void Seed(WebAPI.Models.WebAPIContext context)
        {
            context.Types.AddOrUpdate(t => t.Type_id,
                new Models.Type() { Type_id = 1, name = "Cafe", description = "Loại hình cafe"},
                new Models.Type() { Type_id = 2, name = "Quán ăn", description = "Loại hình quán ăn"},
                new Models.Type() { Type_id = 3, name = "Nhà trọ", description = "Loại hình nhà trọ"}
                );
            //context.Cities.AddOrUpdate(c => c.city_id,
            //    new City() { city_id = 1, name = "Hồ Chí Minh", atribute_name = "Hồ Chí Minh"},
            //    new City() { city_id = 2, name = "Vũng Tàu", atribute_name = "Vũng Tàu"},
            //    new City() { city_id = 3, name = "Đà Lạt", atribute_name = "Đà Lạt"}
            //    );
            //context.Districts.AddOrUpdate(d => d.district_id,
            //    new District() { district_id = 1, name = "Quận 1", city_id = 1},
            //    new District() { district_id = 2, name = "Quận 2", city_id = 1},
            //    new District() { district_id = 3, name = "Quận 3", city_id = 1}
            //    );
            //context.Wards.AddOrUpdate(w => w.ward_id,
            //    new Ward() { ward_id = 1, name = "Phường 1", city_id = 1, district_id = 1},
            //    new Ward() { ward_id = 2, name = "Phường 2", city_id = 1, district_id = 2 },
            //    new Ward() { ward_id = 3, name = "Phường 3", city_id = 1, district_id = 1 }
            //    );
            //context.Places.AddOrUpdate(p => p.id,
            //    new Place() { id = 1, Address = "ABC", City = 1, Description = "Miêu tả ABC",
            //        District = 1, Latitude = 0, Longitude = 0, Phone = "01687654521", Ward = 1, type = 1}
            //    );
            //context.Images.AddOrUpdate(p => p.ImgID,
            //    new Image() { ImgID = 1, PlaceID = 1, URL = "url test"}
            //    );
        }
    }
}
