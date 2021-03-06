USE [master]
GO
/****** Object:  Database [Wine]    Script Date: 3/25/2020 9:21:42 PM ******/
CREATE DATABASE [Wine]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Wine', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\Wine.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Wine_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\Wine_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [Wine] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Wine].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Wine] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Wine] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Wine] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Wine] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Wine] SET ARITHABORT OFF 
GO
ALTER DATABASE [Wine] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Wine] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Wine] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Wine] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Wine] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Wine] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Wine] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Wine] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Wine] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Wine] SET  ENABLE_BROKER 
GO
ALTER DATABASE [Wine] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Wine] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Wine] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Wine] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Wine] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Wine] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Wine] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Wine] SET RECOVERY FULL 
GO
ALTER DATABASE [Wine] SET  MULTI_USER 
GO
ALTER DATABASE [Wine] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Wine] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Wine] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Wine] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Wine] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'Wine', N'ON'
GO
ALTER DATABASE [Wine] SET QUERY_STORE = OFF
GO
USE [Wine]
GO
/****** Object:  Table [dbo].[account]    Script Date: 3/25/2020 9:21:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[account](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[accountDetailId] [int] NULL,
	[email] [nvarchar](150) NULL,
	[password] [nvarchar](50) NULL,
	[status] [bit] NULL,
 CONSTRAINT [PK_account] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[account_detail]    Script Date: 3/25/2020 9:21:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[account_detail](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](150) NULL,
	[mobile] [int] NULL,
	[gender] [int] NULL,
	[address] [nvarchar](150) NULL,
 CONSTRAINT [PK_account_detail] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[category]    Script Date: 3/25/2020 9:21:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[category](
	[cate_id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](150) NULL,
 CONSTRAINT [PK_category] PRIMARY KEY CLUSTERED 
(
	[cate_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[customer_info]    Script Date: 3/25/2020 9:21:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[customer_info](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](150) NULL,
	[mobile] [nvarchar](150) NULL,
	[email] [nvarchar](150) NULL,
	[address] [nvarchar](500) NULL,
 CONSTRAINT [PK_customer_info] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[images]    Script Date: 3/25/2020 9:21:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[images](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[product_id] [int] NULL,
	[imagename] [nvarchar](50) NULL,
	[cover] [bit] NULL,
 CONSTRAINT [PK_images] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[order]    Script Date: 3/25/2020 9:21:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[order](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[customer_info_id] [int] NULL,
	[total_price] [float] NULL,
	[create_date] [nvarchar](50) NULL,
	[note] [nvarchar](250) NULL,
	[status] [int] NULL,
 CONSTRAINT [PK_order] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[order_detail]    Script Date: 3/25/2020 9:21:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[order_detail](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[order_id] [int] NULL,
	[product_id] [int] NULL,
	[product_name] [nvarchar](250) NULL,
	[product_price] [decimal](18, 0) NULL,
	[quantity] [int] NULL,
 CONSTRAINT [PK_order_detail] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product]    Script Date: 3/25/2020 9:21:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[cate_id] [int] NULL,
	[name] [nvarchar](250) NULL,
	[price] [float] NULL,
	[quantity] [int] NULL,
	[size] [nvarchar](150) NULL,
	[color] [nvarchar](50) NULL,
	[description] [nvarchar](550) NULL,
	[status] [int] NULL,
 CONSTRAINT [PK_product] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[account] ON 

INSERT [dbo].[account] ([id], [accountDetailId], [email], [password], [status]) VALUES (1, 1, N'ad@gmail.com', N'123', 1)
INSERT [dbo].[account] ([id], [accountDetailId], [email], [password], [status]) VALUES (2, 2, N'ad', N'1', 0)
INSERT [dbo].[account] ([id], [accountDetailId], [email], [password], [status]) VALUES (3, 1, N'sa@gmail.com', N'1', 1)
INSERT [dbo].[account] ([id], [accountDetailId], [email], [password], [status]) VALUES (4, 2, N'sa@gmail.com', N'1', 1)
INSERT [dbo].[account] ([id], [accountDetailId], [email], [password], [status]) VALUES (1002, 1002, N'safff@gmail.com', N'1', 1)
SET IDENTITY_INSERT [dbo].[account] OFF
SET IDENTITY_INSERT [dbo].[account_detail] ON 

INSERT [dbo].[account_detail] ([id], [name], [mobile], [gender], [address]) VALUES (1, N'sdd', 12323233, 0, N'tb')
INSERT [dbo].[account_detail] ([id], [name], [mobile], [gender], [address]) VALUES (2, N'sdd', 12323233, 0, N'tb')
INSERT [dbo].[account_detail] ([id], [name], [mobile], [gender], [address]) VALUES (1002, N'sddaaaa', 12323233, 0, N'dsad')
SET IDENTITY_INSERT [dbo].[account_detail] OFF
SET IDENTITY_INSERT [dbo].[category] ON 

INSERT [dbo].[category] ([cate_id], [name]) VALUES (1, N'Brandy')
INSERT [dbo].[category] ([cate_id], [name]) VALUES (2, N'Whisky')
INSERT [dbo].[category] ([cate_id], [name]) VALUES (3, N'Rượu Gin')
INSERT [dbo].[category] ([cate_id], [name]) VALUES (4, N'Vodka')
INSERT [dbo].[category] ([cate_id], [name]) VALUES (5, N'Rum')
INSERT [dbo].[category] ([cate_id], [name]) VALUES (6, N'Tequila')
SET IDENTITY_INSERT [dbo].[category] OFF
SET IDENTITY_INSERT [dbo].[images] ON 

INSERT [dbo].[images] ([id], [product_id], [imagename], [cover]) VALUES (1, 1, N'Rivalet_VSOP180.jpg', 1)
INSERT [dbo].[images] ([id], [product_id], [imagename], [cover]) VALUES (2, 2, N'Louis_Martin_XO_.jpg', 1)
INSERT [dbo].[images] ([id], [product_id], [imagename], [cover]) VALUES (3, 3, N'ST_Remy_VSOP_2.jpg', 1)
INSERT [dbo].[images] ([id], [product_id], [imagename], [cover]) VALUES (4, 4, N'400_4_ST_Remy_XO.jpg', 1)
INSERT [dbo].[images] ([id], [product_id], [imagename], [cover]) VALUES (5, 5, N'caravelle.jpg', 1)
INSERT [dbo].[images] ([id], [product_id], [imagename], [cover]) VALUES (6, 6, N'Charles_X_XO.png', 1)
INSERT [dbo].[images] ([id], [product_id], [imagename], [cover]) VALUES (7, 7, N'roya_kingdom_xo.jpg', 1)
INSERT [dbo].[images] ([id], [product_id], [imagename], [cover]) VALUES (8, 8, N'Johnnie.jpg', 1)
INSERT [dbo].[images] ([id], [product_id], [imagename], [cover]) VALUES (9, 9, N'Singleton_18_YO.jpg', 1)
INSERT [dbo].[images] ([id], [product_id], [imagename], [cover]) VALUES (10, 10, N'Singleton_Signature_70cl.jpg', 1)
INSERT [dbo].[images] ([id], [product_id], [imagename], [cover]) VALUES (11, 11, N'Tanqueray_Gin_logo.bmp', 1)
INSERT [dbo].[images] ([id], [product_id], [imagename], [cover]) VALUES (12, 12, N'seagrams_extra_dry_gin_2.png', 1)
INSERT [dbo].[images] ([id], [product_id], [imagename], [cover]) VALUES (13, 13, N'BOMBAY_SAPPHIRE_GIN_750ML.jpg', 1)
INSERT [dbo].[images] ([id], [product_id], [imagename], [cover]) VALUES (14, 14, N'1_Ginistanbul.jpg', 1)
INSERT [dbo].[images] ([id], [product_id], [imagename], [cover]) VALUES (15, 15, N'Smirnoff_o_Vodka.jpg', 1)
INSERT [dbo].[images] ([id], [product_id], [imagename], [cover]) VALUES (16, 16, N'Grey_Goose_Origina.jpg', 1)
INSERT [dbo].[images] ([id], [product_id], [imagename], [cover]) VALUES (17, 17, N'vodka_platinum_pt.jpg', 1)
INSERT [dbo].[images] ([id], [product_id], [imagename], [cover]) VALUES (18, 18, N'vodkadanzka_1.jpg', 1)
INSERT [dbo].[images] ([id], [product_id], [imagename], [cover]) VALUES (19, 19, N'Carta_oro.jpg', 1)
INSERT [dbo].[images] ([id], [product_id], [imagename], [cover]) VALUES (20, 20, N'Captain_Morgan_Gold_75cl_900x900.jpg', 1)
INSERT [dbo].[images] ([id], [product_id], [imagename], [cover]) VALUES (21, 21, N'havana_blanco_chon.jpg', 1)
INSERT [dbo].[images] ([id], [product_id], [imagename], [cover]) VALUES (22, 22, N'Olmeca_Tequila_Reposado_750_1.jpg', 1)
SET IDENTITY_INSERT [dbo].[images] OFF
SET IDENTITY_INSERT [dbo].[product] ON 

INSERT [dbo].[product] ([id], [cate_id], [name], [price], [quantity], [size], [color], [description], [status]) VALUES (1, 1, N'Rượu Rivalet VSOP', 500000, 10, N'750ml, 40%', N'Black', N'Để có được chai rượu Rivalet VSOP phải  ép những trái nho nguyên chất để  lên men và qua quá trình  chưng cất kết hợp với  cồn nguyên chất cùng với Brandy nho nhập khẩu từ Pháp đã được ủ hóa trên 1 năm trong thùng gỗ sồi mới ra được loại rượu ngon hảo hạng...', 1)
INSERT [dbo].[product] ([id], [cate_id], [name], [price], [quantity], [size], [color], [description], [status]) VALUES (2, 1, N'Louis Martin XO', 700000, 20, N'700ml, 40%', N'Red', N'Một loại rượu đặc biệt từ pháp. Được trưng cất từ những loại nho thượng hạng của pháp. Trên 5 năm nằm trong thùng gỗ sồi nên cho ra một hương vị rượu đặc biệt. Dáng chai thanh mảnh hợp với những đường nét nhẹ nhàng tạo lên sự thanh lịch sang trọng.', 1)
INSERT [dbo].[product] ([id], [cate_id], [name], [price], [quantity], [size], [color], [description], [status]) VALUES (3, 1, N'RƯỢU ST REMY VSOP', 300000, 25, N'700ml, 40%', N'Black', N'RƯỢU ST REMY VSOP thuộc dòng rượu Brandy , được chưng cất từ Nho tại nước Pháp đặc biệt Varietal là loại rượu vang từ những vườn nho huyền thoại của nước Pháp . ', 1)
INSERT [dbo].[product] ([id], [cate_id], [name], [price], [quantity], [size], [color], [description], [status]) VALUES (4, 1, N'RƯỢU ST REMY XO', 400000, 10, N'700ml, 40%', N'Red', N'RƯỢU ST REMY XO Như một bó hoa phát triển cao là phong phú và phức tạp đưa ra hương vị của trái cây , Hoa , vani , hương của gỗ Sồi , Gingerbread và Khô mơ vị hơi cay .', 1)
INSERT [dbo].[product] ([id], [cate_id], [name], [price], [quantity], [size], [color], [description], [status]) VALUES (5, 1, N'RƯỢU 16 TH''S CARAVELLE XO', 550000, 12, N'700ml, 43%', N'Gold', N'RƯỢU 16 TH''S CARAVELLE với mong muốn từ nhà sản xuất gửi đến khách hàng những người ưa chuộng dòng sản phẩm RƯỢU 16 TH''S CARAVELLE luôn "thuộn buồm, xuôi gió" như con thuyền dũng mãnh ra khơi chinh phục những miền đấy mới', 1)
INSERT [dbo].[product] ([id], [cate_id], [name], [price], [quantity], [size], [color], [description], [status]) VALUES (6, 1, N'Charles X XO', 800000, 5, N'700ml, 40%', N'Red', N'Qua chưng cất đã được giữ trên 1 năm trong các thùng gỗ nhập khẩu từ Pháp Được sản xuất từ nước ép trái nho lên men ', 1)
INSERT [dbo].[product] ([id], [cate_id], [name], [price], [quantity], [size], [color], [description], [status]) VALUES (7, 1, N'Rượu Royal Kingdom', 500000, 10, N'700ml, 39,5%', N'Gold', N'Rượu Royal Kingdom như một chiếc vương miện lóng lánh , sang trọng, quy phái điều mà mọi khách hàng luôn hướng tới', 1)
INSERT [dbo].[product] ([id], [cate_id], [name], [price], [quantity], [size], [color], [description], [status]) VALUES (8, 2, N'Johnnie Walker Blue Label ', 3600000, 5, N'750ml, 43%', N'Red', N'Rượu Johnnie Walker Blue Label là thành tựu cao nhất của dòng họ Walker. Mỗi thành phần nguyên liệu của hương vị tuyệt vời này được chọn lọc cực kỳ kỹ càng từ những nhà máy chưng cất rượu trứ danh và chỉ từ những thùng rượu tuyệt vời nhất .', 1)
INSERT [dbo].[product] ([id], [cate_id], [name], [price], [quantity], [size], [color], [description], [status]) VALUES (9, 2, N'Singleton 18 years', 2650000, 6, N'750ml, 43%', N'Brown', N'Rượu Singleton 18 _ Loại Whisky mạch nha đơn có hương vị đậm đà , êm dịu và cân bằng hoàn hảo , với thiết kế chai thủy tinh xanh độc đáo theo phong cách của thế kỷ 19 , một thức uống tinh tế , và là món quà tao nhã dành cho những ai biết tận hưởng những điều giản dị...', 1)
INSERT [dbo].[product] ([id], [cate_id], [name], [price], [quantity], [size], [color], [description], [status]) VALUES (10, 2, N'RƯỢU SINGLETON SIGNATURE', 1200000, 8, N'700ml, 40%', N'Gold', N'Rượu Singleton Signature còn được gọi với cái tên quen thuộc "Sinleton Chữ Ký" là sản phẩm có hương vị hiện đại trẻ trung . Dòng sản phẩm đặc sắc này được ủ trong những thùng gỗ Sồi Châu Á và Châu Mỹ với thời gian không dưới 12 năm .', 1)
INSERT [dbo].[product] ([id], [cate_id], [name], [price], [quantity], [size], [color], [description], [status]) VALUES (11, 3, N'Rượu Tanqueray', 500000, 6, N'750ml, 47,3%', N'White', N'Tanqueray Gin có hương vị khá trung tính và tuyệt đối tinh khiết tạo nên một loại " Rượu nền " hài hòa của phần lớn các loại Coktail .', 1)
INSERT [dbo].[product] ([id], [cate_id], [name], [price], [quantity], [size], [color], [description], [status]) VALUES (12, 3, N'Seagram''s Extra Dry Gin', 250000, 18, N'750ml, 40%', N'White', N'Seagram''s Extra Dry Gin là một trong những thương hiệu rượu gin hàng đầu trên thế giới. Được phát triển bởi James Burrough, một dược sĩ, vào năm 1820, quy trình chưng cất của Beefeater vẫn giữ được những giá trị truyền thống cho đến ngày nay.', 1)
INSERT [dbo].[product] ([id], [cate_id], [name], [price], [quantity], [size], [color], [description], [status]) VALUES (13, 3, N'BOMBAY SAPPHIRE', 400000, 20, N'750ml, 47%', N'White', N'Rượu Bombay Sapphire là loại rượu Gin cao cấp dùng để pha chế Cocktail , một trong những loại rượu Gin của nước Anh có doanh số bán ra nhiều nhất Thế giới ', 1)
INSERT [dbo].[product] ([id], [cate_id], [name], [price], [quantity], [size], [color], [description], [status]) VALUES (14, 3, N'Gin Istanbul', 130000, 10, N'700ml, 40%', N'White', N'Rượu gin được chưng cất từ các loại ngũ cốc lên men (bắp, lúa mạch, yến mạch hoặc lúa mạch đen) Xuất xứ: Rượu Thỗ Nhĩ Kỳ', 1)
INSERT [dbo].[product] ([id], [cate_id], [name], [price], [quantity], [size], [color], [description], [status]) VALUES (15, 4, N'Rượu Vodka Smirnoff Red', 230000, 8, N'700ml, 39%', N'White', N'Rượu Vodka Smirnoff Đỏ có mầu trong vắt , hương thơm nồng nàn phảng phất của mùi Cồn và Vani , hương vị ngọt dịu đặc trưng của Ngũ cốc , thoáng vị Khói , dư vị dài lâu thoáng vị Tiêu .', 1)
INSERT [dbo].[product] ([id], [cate_id], [name], [price], [quantity], [size], [color], [description], [status]) VALUES (16, 4, N'Vodka Grey Goose 1500ml', 1500000, 10, N'1500ml, 40%', N'White', N' Rượu được Chưng cất và đóng chai tại Pháp , Rượu Grey Goose này được làm từ lúa mì mùa đông của Pháp từ trong và xung quanh Picardy , một khu vực phía bắc của Paris', 1)
INSERT [dbo].[product] ([id], [cate_id], [name], [price], [quantity], [size], [color], [description], [status]) VALUES (17, 4, N'Rượu Vodka Platinum Pt', 170000, 5, N'700ml, 40%', N'White', N'Rượu Vodka Plantinum Pt là sự kết hợp tinh túy nhất giữa truyền thống sản xuất rượu Vodka của nước Nga với công nghệ khoa học kỹ thuật hiện đại của nhà máy rượu Standart . Rượu Vodka Pt có nồng độ 30 % hoàn toàn dịu nhẹ rất phù hợp với thị hiếu của nhiều người ....', 1)
INSERT [dbo].[product] ([id], [cate_id], [name], [price], [quantity], [size], [color], [description], [status]) VALUES (18, 4, N'Rượu Danzka Vodka', 300000, 9, N'750ml, 40%', N'White', N'Rượu Vodka Danzka có thiết kế chai nhôm giữ lạnh giữ nhiệt lâu nên bạn có thể thưởng thức dòng Vodka này tựa như nước Sông băng . Danzka Vodka có xuất xứ từ Đan Mạch , với vị êm mượt , hương phong phú cùng đặc tính nhẹ của Trái cây .', 1)
INSERT [dbo].[product] ([id], [cate_id], [name], [price], [quantity], [size], [color], [description], [status]) VALUES (19, 5, N'Rhum Bacardi Vàng', 350000, 5, N'750ml, 40%', N'Yellow', N'Bacardi là thương hiệu rượu Rhum bán chạy nhất Thế giới có giá trị tới 3 tỷ USD . Rhum Bacardi Vàng là dòng rượu pha chế cho phép người uống có thể thưởng thức theo nhiều cách khác nhau từ kiểu pha chế Cocktail cho đến cách uống pha với Soda hay Cocacola .', 1)
INSERT [dbo].[product] ([id], [cate_id], [name], [price], [quantity], [size], [color], [description], [status]) VALUES (20, 5, N'Rượu Captain Morgan', 400000, 8, N'750ml, 35%', N'Yellow', N'Captain Morgan là loại rượu Rum nhẹ của Jamaica , được sản xuất từ mật mía lên men tự nhiên , sau đó được chưng cất trong nồi đồng 2 lần , rồi được ủ trong thùng gỗ Sồi trong khoảng thời gian từ 6 tháng đến 4 năm . ', 1)
INSERT [dbo].[product] ([id], [cate_id], [name], [price], [quantity], [size], [color], [description], [status]) VALUES (21, 5, N'RƯỢU HAVANA CLUB ANEJO BLANCO ( TRẮNG )', 350000, 9, N'750ml, 40%', N'White', N'Rượu Havana Club Anejo Blanco còn được gọi " Havana Club Trắng" , là loại rượu Rhum có xuất xứ từ Cu Ba , được dùng cho pha chế Cocktails cũng như có thể uống trực tiếp hoặc dùng là gia vị cho các món ăn ', 1)
INSERT [dbo].[product] ([id], [cate_id], [name], [price], [quantity], [size], [color], [description], [status]) VALUES (22, 6, N'TEQUILA OLMECA', 400000, 8, N'750ml, 38%', N'Brown', N'Tequila là loại rượu mạnh của Mexico có mùi thơm hăng hăng , đắng và hơi chát , hấp dẫn cả Thế giới qua hàng trăm năm từ khi ra đời . ', 1)
SET IDENTITY_INSERT [dbo].[product] OFF
USE [master]
GO
ALTER DATABASE [Wine] SET  READ_WRITE 
GO
