namespace WebAPI.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class Initial : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.Images",
                c => new
                    {
                        ImgID = c.Int(nullable: false, identity: true),
                        URL = c.String(nullable: false),
                        PlaceID = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.ImgID)
                .ForeignKey("dbo.Places", t => t.PlaceID, cascadeDelete: true)
                .Index(t => t.PlaceID);
            
            CreateTable(
                "dbo.Places",
                c => new
                    {
                        id = c.Int(nullable: false, identity: true),
                        Name = c.String(nullable: false),
                        Description = c.String(),
                        Address = c.String(),
                        Latitude = c.Single(nullable: false),
                        Longitude = c.Single(nullable: false),
                        Phone = c.String(),
                        City = c.Int(nullable: false),
                        District = c.Int(nullable: false),
                        Ward = c.Int(nullable: false),
                        type = c.Int(nullable: false),
                        Type_Type_id = c.Int(),
                    })
                .PrimaryKey(t => t.id)
                .ForeignKey("dbo.Types", t => t.Type_Type_id)
                .Index(t => t.Type_Type_id);
            
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.Images", "PlaceID", "dbo.Places");
            DropForeignKey("dbo.Places", "Type_Type_id", "dbo.Types");
            DropIndex("dbo.Places", new[] { "Type_Type_id" });
            DropIndex("dbo.Images", new[] { "PlaceID" });
            DropTable("dbo.Places");
            DropTable("dbo.Images");
        }
    }
}
