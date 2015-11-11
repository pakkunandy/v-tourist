namespace WebAPI.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class Initial : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.Types",
                c => new
                    {
                        Type_id = c.Int(nullable: false, identity: true),
                        name = c.String(nullable: false),
                        description = c.String(),
                    })
                .PrimaryKey(t => t.Type_id);
            
        }
        
        public override void Down()
        {
            DropTable("dbo.Types");
        }
    }
}
