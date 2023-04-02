use master
go

if exists(select * from sys.databases where name = 'SHOPF')
	drop database SHOPF
	go
create database SHOPF
go
use SHOPF

create table Accounts(
	Fullname nvarchar(100),
	Username varchar(50),
	Email varchar(100),
	Photo varchar(50),
	Phone varchar(10),
	Password varchar(40),
	Active bit,
	primary key(Username)
)


create table Reviews(
	Id int identity,
	Username varchar(50),
	Review nvarchar(4000),
	CreateDate date,
	primary key(Id),
	constraint fk_Reviews_idaccount
	foreign key(Username) references Accounts(Username)
)

create table Activities(
	Id int identity,
	CreateDate date,
	Activity nvarchar(200) null,
	primary key(Id)
)

create table News(
	Id int identity,
	Name nvarchar(80),
	Image nvarchar(80),
	Description nvarchar(500),
	Opening nvarchar(4000),
	Body1 nvarchar(4000) null,
	Body2 nvarchar(4000) null,
	Ending nvarchar(4000),
	CreateDate date,
	Writer nvarchar(200) null,
	primary key(Id)
)


create table Roles(
	id varchar(10),
	Name nvarchar(50),
	primary key(id)
)

create table Authorities(
	id int identity,
	Username varchar(50),
	Roleid varchar(10)
	primary key(id),
	constraint fk_Authorities_idaccount
	foreign key(Username) references Accounts(Username),
	constraint fk_Authorities_idrole 
	foreign key(Roleid) references Roles(id)
)

create table Categories(
	Id varchar(15),
	Name nvarchar(50) 
	primary key(Id)
)

create table Products(
	Id int identity,
	Name nvarchar(200),
	Image nvarchar(80),
	Image1 nvarchar(80),
	Image2 nvarchar(80),
	Image3 nvarchar(80),
	Price float,
	Quantity int,
	Describe1 nvarchar(1000),
	Describe2 nvarchar(4000),
	Categoryid varchar(15),
	CreateDate date,
	primary key(Id),
	constraint fk_Products_maloaihang
	foreign key(Categoryid) references Categories(Id)
)

create table Product_Reviews(
	Id int identity,
	Username varchar(50),
	Productid int,
	Review nvarchar(4000),
	CreateDate date,
	primary key(Id),
	constraint fk_pdreviews_idaccount
	foreign key(Username) references Accounts(Username),
	constraint fk_pdreviews_idproduct
	foreign key(Productid) references Products(Id) on DELETE CASCADE
)

create table Statuss(
	Id nvarchar(50),
	Status nvarchar(50) 
	primary key(Id)
)

create table Orders(
	id bigint identity,
	Username varchar(50),
	Createdate date,
	Address nvarchar(100),
	Status nvarchar(50),
	Paymentstatus nvarchar(400),
	primary key(id),
	constraint fk_Orders_idnguoidung
	foreign key(Username) references Accounts(Username)
)

create table Order_Detail(
	id bigint identity,
	Orderid bigint,
	Productid int,
	Price float,
	Quantity int
	primary key(id),
	constraint fk_OrderDetail_idhoadon
	foreign key(Orderid) references Orders(id),
	constraint fk_OrderDetail_masp
	foreign key(Productid) references Products(Id) on DELETE CASCADE
)

create table Translation(
	id bigint identity,
	Amount float,
	Bank_code varchar(20),
	Card_type varchar(20),
	Translation_info varchar(100),
	Pay_date date,
	Pay_time time,
	Pay_status nvarchar(200),
	Translation_no bigint,
	Order_id bigint,
	Bank_tran_no varchar(100),
	primary key(id),
	constraint fk_Translation_idhoadon
	foreign key(Order_id) references Orders(id)
)


create table Favorites(
	id int identity,
	Username varchar(50),
	Productid int, 
	Have varchar(10),
	primary key(id),
	constraint fk_Favorites_idaccount
	foreign key(Username) references Accounts(Username),
	constraint fk_Favorites_idproduct
	foreign key(Productid) references Products(id) on DELETE CASCADE
)


---------------------------------------------------------------------------


insert Accounts (Fullname, Username, [Password], Email, Photo,Phone,Active) values
		(N'ADMIN',N'ADMIN','123', 'admin@gmail.com', 'hinh.png','0985212452','1'),
		(N'STAFF1',N'STAFF1','123', 'staff1@gmail.com', 'hinh.png','0985212452','1'),
		(N'STAFF2',N'STAFF2','123', 'staff2@gmail.com', 'hinh.png','0985212452','1'),
		(N'STAFF3',N'STAFF3','123', 'staff3@gmail.com', 'hinh.png','0985212452','0'),
		(N'STAFF4',N'STAFF4','123', 'staff4@gmail.com', 'hinh.png','0985212452','1'),
		(N'STAFF5',N'STAFF5','123', 'staff5@gmail.com', 'hinh.png','0985212452','1'),

		(N'Trần Nguyên Linh','linhpc12','linh123', 'linh@gmail.com', 'hinh.png','0985212452','1'),
		(N'Lê Thị Lan','lan123','123', 'lan@gmail.com', 'hinh.png','0985212452','1'),
		(N'Lương Văn Hùng','hung123','123', 'hung@gmail.com', 'hinh.png','0985212452','1'),
		(N'Nguyễn Văn Long',N'long123','123', 'long@gmail.com', 'hinh.png','0985212452','1'),
		(N'Trần Hữu Tài',N'tai123','123', 'tai@gmail.com', 'hinh.png','0985212452','1'),
		(N'Lê Thị Dung',N'dung123','123', 'dung@gmail.com', 'hinh.png','0985212452','1')

insert Roles (id, Name) values
		('ADMIN', N'admin'),
		('STAF', N'staff'),
		('USER', N'user')

insert Authorities (Username, Roleid) values
		('ADMIN','ADMIN'),
		('STAFF1','STAF'),
		('STAFF2','STAF'),
		('STAFF3','STAF'),
		('STAFF4','STAF'),
		--('STAFF5','STAF'),
		('tai123','USER')
insert News ([Name], [Image], [Description], Opening, Body1, Body2, Ending, CreateDate, Writer) values
	(N'Bí quyết săn sale Black Friday 2022', 'bfd.jpg', N'Sự kiện Black Friday 2022 là cơ hội để bạn chốt đơn đồ 
	chơi trẻ em hàng hiệu với giá rẻ.Hãy bỏ túi những bí quyết đỉnh cao để săn sale thật hiệu quả', N'Mặc dù ngày chính thức của sự kiện Black Friday 2022 là 25/11 nhưng đa số thương hiệu thường tung các deal hot trước đó 
	vài ngày hoặc 1 tuần. Vậy nên để không bỏ lỡ các sản phẩm xịn giá tốt nhất, thì bạn nên theo dõi thường xuyên để nhanh tay rinh về sớm nhất 
	có thể. ', N'Lên sẵn danh sách những món hàng bạn yêu thích và muốn mua cũng là một lựa chọn tuyệt vời trong ngày hội mua sắm, khi lên sẵn danh 
	sách những món đồ muốn mua bạn sẽ giúp bạn mua được những món đồ ưng ý với giá tốt và loại bỏ những món đồ không cần thiết', N'Ngoài ra bạn còn 
	phải chất lượng của đồ chơi vì vậy hãy chọn những thương hiệu lớn như Fisher Price, Leapfrog, Baby Alive, Bruder, Siku, LEGO, Playdoh... Điều này vừa
	đáp ứng được chất lượng và độ an toàn tới sức khoẻ', N'Đặc biệt bạn nên chú ý tới những chính sách mua hàng trong sự kiện Black Friday và rủ bạn bè gộp 
	đơn để mua hàng được những ưu đãi giảm giá nhiều nhất', '2022/12/11', 'ADMIN'),

	(N'Tìm hiểu black friday là gì và khi nào diễn ra', 'bf.jpg', N'Vào thời điểm gần cuối năm, các thương hiệu lớn liên tục tung ra nhiều chương trình ưu 
	đãi siêu hấp dẫn để hưởng ứng ngày hội mua sắm Black Friday. ', N'Black Friday thường diễn ra vào ngày thứ sáu của tuần thứ 4 trong tháng 11, sau ngày Lễ 
	Tạ Ơn hàng năm.Vào ngày này, hàng trăm ngàn sản phẩm đến từ nhiều thương hiệu lớn nhỏ sẽ được giảm giá cực sốc giúp người tiêu dùng mua sắm thả ga. Đây được 
	xem là một phần của văn hóa Mỹ và đã phổ biến tại nhiều quốc gia trên thế giới, trong đó có Việt Nam.', N'Ngày Black Friday năm 2022 sẽ diễn ra chính thức 
	vào ngày 25/11. Tuy nhiên hiện nay Black Friday không chỉ diễn ra duy nhất một ngày mà có thể kéo dài trong vài ngày, thậm chí vài tuần tùy vào chương trình 
	của từng công ty và thương hiệu. Bạn có thể nắm bắt lịch săn sale Black Friday 2022 của thương hiệu mình yêu thích bằng việc theo dõi fanpage chính thức hoặc 
	các thông tin trên các banner quảng cáo.', N'Ở Việt Nam, thời điểm diễn ra ngày hội mua sắm Black Friday rất gần với Lễ Giáng Sinh, Tết Dương Lịch, Tết Nguyên 
	Đán. Vì vậy, nhu cầu mua sắm các đồ dùng, quà tặng luôn tăng cao. Đây là cơ hội có một không hai để bạn săn những món đồ mà mình yêu thích hoặc dành tặng người 
	thân, bạn bè với ưu đãi siêu hấp dẫn lên tới 50%, thậm chí 70%...', N'Để tận dụng Black Friday và tiết kiệm chi phí mua hàng bạn nên tìm hiểu những chính sách 
	của nhãn hàng bạn muốn mua, rủ bạn bè gộp đơn hàng và mua những món đồ có giá trị cao', '2022/12/12', 'ADMIN'),

	(N'Siêu hot đồ chơi an toàn cho bé giảm giá đến 50%', 'bfsale.jpg', N'Siêu hội sale mang đến vô số sản phẩm hấp dẫn 
	cho bé thỏa sức lựa chọn, từ những món đồ dùng hữu ích cho đến đồ chơi giáo dục, đồ chơi vận động...', N'Ba mẹ có thể an tâm mua sắm đồ chơi trẻ em chính hãng 100% 
	và được đảm bảo chất lượng từ các thương hiệu nổi tiếng trên thế giới như FISHER PRICE, BATTAT, PEEK A BOO, ROYAL BABY, LITTLE TIKES, LEAPFROG... với mức giảm cực 
	sâu đến 50%.', N'Đặc biệt, ba mẹ còn được tặng voucher mua xe đạp trị giá 500,000đ với hóa đơn mua hàng từ 1,000,000đ nữa đấy! Lưu ý, voucher chỉ áp dụng cho hàng 
	xe đạp nguyên giá, không áp dụng cho xe đạp thăng bằng.', N'Đến với CityZen ba mẹ có thể thoải mái lựa chọn những món đồ chơi với nhiều mẫu mã đa dạng khác nhau đảm bảo được
	chất lượng và sở thích của trẻ em.', N'Ba mẹ có thể tham khảo thêm nhiều đồ chơi trẻ em khác được giảm tới 50% tại sự kiện . Lưu ý chương trình diễn ra 
	trong 15 ngày vàng từ ngày 16/12 đến hết ngày 30/12. Vì vậy ba mẹ hãy nhanh tay mua ngay những sản phẩm ưng ý để không bỏ lỡ ưu đãi hấp dẫn nhất.', '2022/12/13', 'ADMIN'),
	
	(N'Đón Black Friday cùng CityZen Store ', 'z.jpg', N'Sự kiện Black Friday 2022 là cơ hội để bạn chốt đơn đồ 
	chơi trẻ em hàng hiệu với giá rẻ.Hãy bỏ túi những bí quyết đỉnh cao để săn sale thật hiệu quả', N'Mặc dù ngày chính thức của sự kiện Black Friday 2022 là 25/11 nhưng đa số thương hiệu thường tung các deal hot trước đó 
	vài ngày hoặc 1 tuần. Vậy nên để không bỏ lỡ các sản phẩm xịn giá tốt nhất, thì bạn nên theo dõi thường xuyên để nhanh tay rinh về sớm nhất 
	có thể. ', N'Lên sẵn danh sách những món hàng bạn yêu thích và muốn mua cũng là một lựa chọn tuyệt vời trong ngày hội mua sắm, khi lên sẵn danh 
	sách những món đồ muốn mua bạn sẽ giúp bạn mua được những món đồ ưng ý với giá tốt và loại bỏ những món đồ không cần thiết', N'Ngoài ra bạn còn 
	phải chất lượng của đồ chơi vì vậy hãy chọn những thương hiệu lớn như Fisher Price, Leapfrog, Baby Alive, Bruder, Siku, LEGO, Playdoh... Điều này vừa
	đáp ứng được chất lượng và độ an toàn tới sức khoẻ', N'Đặc biệt bạn nên chú ý tới những chính sách mua hàng trong sự kiện Black Friday và rủ bạn bè gộp 
	đơn để mua hàng được những ưu đãi giảm giá nhiều nhất', '2022/12/14', 'ADMIN'),

	(N'Những lợi ích tuyệt vời của đồ chơi LEGO', 'loiichlego.jpg', N'LEGO là một thương hiệu 
	đồ chơi lắp ráp trẻ em của Đan Mạch ra đời từ năm 1932. LEGO trong tiếng Đan Mạch được viết tắt của từ LEG GODT
	nghĩa là CHƠI HAY', N'LEGO bao gồm những mảnh ghép có nhiều hình dạng khác nhau khi được ghép lại sẽ tạo ra những mô hình 
	đẹp đẽ tuỳ theo trí tưởng tượng và sáng tạo của người chơi.Đồ chơi LEGO cho bé được chứng minh là món đồ chơi thông minh 
		mang lại nhiều lợi ích tuyệt vời đối với sự phát triển của trẻ.', N'Những lợi ích của đồ chơi LEGO mang lại cho bé vô cùng lớn.
	Khi chơi trò chơi LEGO đòi hỏi trẻ phải thật tập trung, tỉ mỉ quan sát để tạo ra được những mô hình đẹp mắt nhất. Nhờ vậy sẽ rèn 
luyện cho trẻ khả năng quan sát, tăng khả năng sáng tạo để hoàn thành được những mô hình độc đáo theo trí tưởng tượng của trẻ', N'
	Chơi Lego là cách vô cùng hiệu quả để rèn luyện và phát triển kỹ năng giao tiếp cũng như kỹ năng xã hội cho trẻ, nhờ vào việc cho 
	trẻ chơi cùng với cá bạn đồng trang lứa hoặc thậm chí là anh chị hay ba mẹ.Ngoài việc có thể phát triển các kỹ năng mềm thì chơi Lego 
	còn giúp trẻ học các phép toán cơ bản. Ví dụ như trẻ có thể đếm số lượng mảnh ghép và xác định xem chúng xếp được thành bao nhiêu hình 
	vuông, tam giác,... Ngoài ra thì xếp hình Lego còn giúp cải thiện tư duy về không gian.', N'Lọi ích mà LEGO đem lại vô cùng lớn đối với 
	trẻ em, lứa tuổi trí tuệ và sức sáng tạo đang được phát triển', '2022/12/10', 'ADMIN')

insert Categories (Id, [Name]) values
	(N'LG',N'Lego'),
	(N'GD',N'Gundam'),
	(N'CB',N'Chibi'),
	(N'FG',N'Figure'),
	(N'ANT',N'Khác')

/** thêm dữ liệu chỗ này(mỗi loại tối thiểu 10 sản phẩm) sửa lại Describe1 và Describe2 của sản phẩm đã thêm**/
insert Products (Quantity, [Name], Price, Image,Image1,Image2,Image3, Categoryid, CreateDate, Describe1,Describe2) values
	(20, N'LEGO Tàu cảnh sát',520000,'tautuantracanhsat.jpg','tautuantracanhsat1.jpg','tautuantracanhsat2.jpg','tautuantracanhsat3.jpg','LG','2022/11/10',N'LEGO CITY Tàu cảnh sát Bộ gạch đồ chơi lắp ráp cho trẻ em',
	N'Đồ chơi LEGO với các tính năng ấn tượng, bao gồm một trạm điều khiển chi tiết và máy bay không người lái giám sát. Trẻ em có thể nhấp nhẹ một đòn bẩy 
	để thả thợ lặn và xe tay ga để bắt kẻ gian. Với 5 nhân vật nhỏ, bao gồm Gracie Goodhart, Frankie Lupelli và Hacksaw Hank từ loạt phim truyền hình LEGO City Adventures, 
	có rất nhiều cảm hứng để bạn nhập vai sáng tạo. Vui vẻ và dễ xây dựng Bao gồm hướng dẫn xây dựng dễ làm theo và Hướng dẫn PLUS - một phần của ứng dụng Hướng dẫn xây dựng
	LEGO miễn phí dành cho điện thoại thông minh và máy tính bảng. Hướng dẫn xây dựng tương tác này, với các công cụ xem thu phóng và xoay, nhanh chóng giúp những người
	xây dựng LEGO mới chớm nở trở thành những nhà xây dựng bậc thầy! Đồ chơi thú vị cho trẻ em Bộ đồ chơi cảnh sát thành phố LEGO mang đến những kịch bản chơi thú vị mô tả 
	cuộc sống thực một cách vui nhộn và giàu trí tưởng tượng, với các phương tiện thực tế và các nhân vật đầy cảm hứng mà trẻ em yêu thích.'),

	(20, N'LEGO Lớp Học Biến Hình',890000,'lophocmonbienhinh.jpg','lophocmonbienhinh1.jpg','lophocmonbienhinh2.jpg','lophocmonbienhinh3.jpg','LG','2022/11/10',N'LEGO HARRY POTTER Lớp Học Môn Biến Hình',N'Lớp Học Môn Biến Hình 
	là một bộ độ chơi chứa bên trong một quyển sách lắp ráp bằng gạch. Trẻ em mở nó ra và ngay lập tức tham gia g lớp học biến hình của Giáo sư McGonagall. Phép thuật của Hogwarts
	khiến cho trẻ em thích thú dù ở bất cứ nơi đâu. Bộ đồ chơi thu hút này chứa đầy các tính năng và phụ kiện để truyền cảm hứng vui chơi nhập vai say mê bất tận.
	Một số được tích hợp sẵn, chẳng hạn như tủ lưu trữ, trong khi một số đồ chơi rời như bàn ghế và bảng đen để có thể nâng ra, mở rộng không gian vui chơi. Bộ Harry Potter kỳ diệu này
	cũng kết hợp với những bộ khác trong bộ sưu tập. Mỗi bộ được thiết kế với một màu sắc đặc biệt và họa tiết độc đáo, mở hộp ra để đưa trẻ đến cuộc phiêu lưu trong các lớp học Hogwarts
    khác nhau. Toàn bộ đồ chơi cũng kết nối để tạo ra trải nghiệm vui chơi 360 độ ở Hogwarts.'),

	(29, N'LEGO Lớp Học Độc Dược',899000,'lophocdocduoc.jpg','lophocdocduoc1.jpg','lophocdocduoc2.jpg','lophocdocduoc3.jpg','LG','2022/11/08',N'LEGO HARRY POTTER Lớp Học Môn Độc Dược',N'LEGO Harry Potter Lớp Học Môn Độc Dược 
	là một bộ đồ chơi trong lớp học Hogwarts ẩn bên trong một quyển sách được lắp ráp bằng gạch. Trẻ em mở quyển sách để tham gia vào bài học môn độc dược của Giáo sư Snape. Bộ đồ chơi kỳ
	diệu với các tính năng và phụ kiện để truyền cảm hứng cho vui chơi đóng vai giàu trí tưởng tượng. Một số được tích hợp sẵn, chẳng hạn như kệ và một số rời, như bàn ghế và bảng đen có 
	thể nâng ra để mở rộng không gian chơi. Bộ đồ chơi Harry Potter đầy mê hoặc này có thể kết hợp với những bộ khác trong bộ sưu tập. Mỗi khi mở ra sẽ đưa trẻ đến một cuộc phiêu lưu trong
	lớp học Hogwarts khác nhau và quyển sách có bìa màu đặc biệt được trang trí bằng họa tiết độc đáo.'),

	(26, N'LEGO Chuồng Ngựa',750000,'chuongngua.jpg','chuongngua1.jpg','chuongngua2.jpg','chuongngua3.jpg','LG','2022/11/06',N'LEGO Minecraft mô hình Chuồng Ngựa',N'LEGO Minecraft Chuồng Ngựa (241 chi tiết) kết hợp tất cả niềm vui và 
	cuộc phiêu lưu của trò chơi trực tuyến phổ biến với khả năng sáng tạo vô tận của những viên gạch LEGO và niềm vui khi chăm sóc những chú ngựa. Những đứa trẻ thích sáng tạo sẽ thích bộ 
	LEGO Minecraft đa năng này. Chuồng có cửa mở và chứa đầy các chi tiết Minecraft đích thực, chẳng hạn như cà rốt vàng và áo giáp quý giá. Có những con ngựa để nuôi và chăm sóc, một chướng 
	ngại vật để thiết kế và một bộ xương kỵ sĩ để chiến đấu! Bộ xây dựng và chơi thực hành, là một món quà lý tưởng cho trẻ em - những người chơi Minecraft cũng như những người yêu thích ngựa.'),

	(20, N'LEGO Tòa Tháp Chọc Trời',750000,'toathapchoctroi.jpg','toathapchoctroi1.jpg','toathapchoctroi2.jpg','toathapchoctroi3.jpg','LG','2022/11/06',N'LEGO Minecraft Tòa Tháp Chọc Trời',N'LEGO Minecraft Tòa Tháp Chọc Trời (565 chi tiết) 
	chứa đầy những pha hành động chân thực và môi trường đa dạng mà trẻ em đam mê Minecraft có thể cấu hình lại nhiều lần để có những chuyến phiêu lưu bất tận trên mây. Trẻ em sử dụng các 
	tính năng thú vị, đích thực của Minecraft để bay lên trời và xây dựng một ngôi nhà của thợ rèn, một tòa tháp cao vút và một hòn đảo trong vườn. Các khả năng chơi bao gồm từ trồng rau 
	cho đến chiến đấu với những bóng ma bay. Bộ mô hình mang đến cho trẻ em những khả năng xây dựng và sáng tạo bất tận.'),

	(50, N'LEGO Khu Rừng Ma Quái',950000,'khurungrammaquai.jpg','khurungrammaquai1.jpg','khurungrammaquai2.jpg','khurungrammaquai3.jpg','LG','2022/11/06',N'LEGO Minecraft Khu Rừng Rậm Ma Quái',N'Không ai được an toàn trong rừng với LEGO Minecraft 
	Khu Rừng Rậm Ma Quái. Bộ xây dựng và chơi sáng tạo này có con quái vật cùng với một loạt các nhân vật và tính năng Minecraft thú vị. Người chơi Minecraft sẽ thích chạm 
	tay vào con quái vật to lớn, và bắt đầu chiến đấu. Với cái đầu khổng lồ, có thể di chuyển, miệng mở và cánh tay được tạo ra để nghiền nát, Jungle Abomination là siêu mô hình rừng Minecraft. 
	Để khuyến khích nhiều cách chơi giàu trí tưởng tượng khác nhau, bộ này cũng bao gồm một nhà thám hiểm Minecraft, nhà khảo cổ học, Creeper bị mê hoặc, bộ xương và một loại cây có thể xây dựng, 
	có khớp nối với những cánh hoa. Bộ lắp ghép với những chi tiết thú vị, tái hiện chân thật quang cảnh sẽ thu hút những fan hâm mộ của Minecraft.'),

	(20, N'LEGO Xe Koenigsegg',690000,'sieuxejesko.jpg','sieuxejesko1.jpg','sieuxejesko2.jpg','sieuxejesko3.jpg','LG','2022/11/06',N'LEGO Speed Champions Siêu Xe Koenigsegg Jesko',N'Trẻ em và những người đam mê ô tô sẽ thích LEGO 
	Speed Champions Siêu Xe Koenigsegg Jesko (280 chi tiết). Đồ chơi mô phỏng chi tiết này thể hiện bản chất của siêu xe Thụy Điển ngoài đời thực với thiết kế khí động học tiên tiến giúp 
	mang lại hiệu suất vượt trội 300 dặm / giờ. Trẻ em có thể khám phá chiếc xe khi họ chế tạo, trước khi đưa nó lên trưng bày hoặc tham gia đường đua để có những pha hành động gay cấn. Mẫu xe 
	sưu tập này đi kèm với khung gầm rộng, cho phép đủ chỗ cho khoang lái 2 chỗ ngồi và thậm chí còn có nhiều chi tiết chân thực hơn. Và với một minifigure người lái xe Koenigsegg hoàn chỉnh với
	bộ đồ đua, mũ bảo hiểm và cờ lê, bạn sẽ có rất nhiều cảm hứng để chơi theo trí tưởng tượng. LEGO Speed ​​Champions s mang đến cho trẻ em và những người hâm mộ ô tô cơ hội thu thập và chế tạo các 
	phiên bản nhỏ của những chiếc ô tô nổi tiếng và hàng đầu thế giới. Hoàn hảo để trưng bày, những chiếc xe này cũng rất tuyệt vời để thực hiện các pha đua xe gay cấn với các loại xe khác trong 
	phạm vi Speed ​​Champions.'),

	(20, N'LEGO Siêu xe đua',1290000,'sieuxedua.jpg','sieuxedua1.jpg','sieuxedua2.jpg','sieuxedua3.jpg','LG','2022/11/06',N'LEGO Siêu Xe Đua Corvette C8.R & 1968 
	Corvette C3',N'Bộ LEGO Speed Champions Siêu Xe Đua Chevrolet Corvette C8.R & 1968 Chevrolet Corvette C3 là một món quà hoàn hảo dành cho những người hâm 
	mộ ô tô.Trẻ em và những người đam mê ô tô có thể thu thập và khám phá 2 chiếc Corvette đột phá từ các thời đại khác nhau. Đóng gói với các chi tiết chân thực, những chiếc xe hoành tráng này 
	rất thú vị khi chế tạo, tuyệt vời để trưng bày và tuyệt vời để đua Những chiếc xe trong bộ Speed Champions này có khung gầm rộng, cho phép có buồng lái 2 chỗ ngồi và thậm chí còn có nhiều 
	chi tiết chân thực hơn. Và với 2 nhân vật nhỏ của người lái xe Chevrolet đã chuẩn bị sẵn sàng để đi, bối cảnh được thiết lập cho các hành động đường đua huyền thoại. Bộ LEGO Speed Champions 
	mang đến bản sao đích thực của những chiếc ô tô sáng tạo nhất và nổi tiếng nhất trên thế giới. Hoàn hảo để trưng bày, các mô hình sưu tập cũng rất tuyệt vời cho các cuộc đua hành động gay 
	cấn với các phương tiện khác trong phạm vi Speed Champions.'),

	(20, N'LEGO Xe Toyota',699000,'sieuxetoyota.jpg','sieuxetoyota1.jpg','sieuxetoyota2.jpg','sieuxetoyota3.jpg','LG','2022/11/06',N'LEGO Speed Champions Siêu Xe Toyota GR Supra',N'LEGO Speed Champions Siêu Xe Toyota GR Supra 
	 mang đến cho trẻ em và những người đam mê ô tô ở mọi lứa tuổi cơ hội thu thập, chế tạo và khám phá một trong những chiếc xe thể thao hiệu suất cao mang tính biểu tượng 
	nhất thế giới. Được đóng gói với các chi tiết thực tế, bản sao đích thực này cung cấp một trải nghiệm xây dựng hấp dẫn, hoàn hảo để hiển thị và tuyệt vời cho hành động đua xe năng lượng cao
	Đồ chơi ô tô sưu tầm này đi kèm với khung gầm rộng, cho phép có chỗ cho khoang lái 2 chỗ ngồi và thậm chí còn có nhiều chi tiết chân thực hơn. Và với cấu hình nhỏ của người lái xe Toyota hoàn 
	chỉnh với bộ đồ đua, mũ bảo hiểm và cờ lê, bạn sẽ có rất nhiều cảm hứng để chơi ở tốc độ cao, giàu trí tưởng tượng. Bộ xây dựng LEGO Speed Champions có các phiên bản nhỏ của những chiếc ô 
	tô nổi tiếng và hàng đầu thế giới. Hoàn hảo để trưng bày, những chiếc xe này cũng rất tuyệt vời để thực hiện các pha đua xe gay cấn với các loại xe khác trong phạm vi Speed Champions.'),

	(20, N'LEGO Phi Cơ',1200000,'phicodua.jpg','phicodua1.jpg','phicodua2.jpg','phicodua3.jpg','LG','2022/11/06',N'LEGO TECHNIC Phi Cơ Đua 2 trong 1 ',N'Mang thế giới ly kỳ của những chiếc máy bay đóng thế trở nên sống động với LEGO TECHNIC
	Phi Cơ Đua này. Món quà dành cho bé trai và bé gái từ 7 tuổi trở lên bao gồm các chi tiết như cánh quạt quay khi máy bay được đẩy cùng, cộng với việc mở nắp động cơ. màu 
	sắc bắt mắt, màu xanh mòng két và màu cam với các chi tiết của nhà tài trợ hoàn thành một món quà nhỏ tuyệt vời cho bất kỳ dịp nào - nó giống như một chiếc máy bay đua thực sự.Mô hình 2 trong 1
	Máy bay đồ chơi này được xây dựng thành Máy bay phản lực để trẻ em có thể tận hưởng niềm vui xây dựng nhiều hơn nữa. Thật dễ dàng để bắt đầu vì bộ này đi kèm với Hướng dẫn PLUS. Có sẵn trong ứng 
	dụng Hướng dẫn xây dựng LEGO miễn phí, hướng dẫn tương tác này bao gồm các công cụ thu phóng và xoay, cùng với chế độ, để giúp trẻ em xây dựng một cách độc lập. Giờ chơi sáng tạo Vũ trụ LEGO Technic 
	cung cấp đồ chơi xây dựng tiên tiến cho những người hâm mộ LEGO nhỏ tuổi, những người đã sẵn sàng cho thử thách tiếp theo. Với các phương tiện trông và hoạt động giống như thật, những bộ này mang đến 
	sự giới thiệu tuyệt vời về kỹ thuật và cơ khí.'),

	(30, N'LEGO Ca nô Cứu Hộ',940000,'canocuuho.jpg','canocuuho1.jpg','canocuuho2.jpg','canocuuho3.jpg','LG','2022/11/06',N'LEGO TECHNIC Ca nô Đệm Khí Cứu Hộ',N'LEGO Technic Ca nô Đệm Khí Cứu Hộ được trang bị đầy đủ các tính năng đích thực, 
	Ca nô Đệm Khí Cứu Hộ hoàn hảo để tái tạo các nhiệm vụ cứu hộ gay cấn. Các bé trai và bé gái từ 8 tuổi trở lên sẽ thích nhìn chiếc xe đồ chơi bay lơ lửng khi nó lăn trên các bánh xe được giấu kín.
	Với quạt quay, đèn cảnh báo, buồng lái có ghế ngồi, bảng điều khiển và tay lái, có rất nhiều điều để khám phá. Và khi trẻ em đã sẵn sàng cho một thử thách mới, đồ chơi Ca nô Đệm Khí Cứu Hộ được lắp 
	ráp lại thành máy bay hai động cơ, được thiết kế với các tính năng thực tế, bao gồm bánh lái chuyển động và cánh nhỏ chuyển động.Đồ chơi lắp ráp để thu hút trí óc trẻ thơ Vũ trụ LEGO Technic bao gồm 
	một loạt các phương tiện đồ chơi thú vị, được thiết kế đặc biệt cho những người hâm mộ LEGO nhỏ tuổi, những người đã sẵn sàng cho thử thách lắp ráp tiếp theo. Với các chức năng và thiết kế thực tế, 
	những mô hình này lý tưởng cho bất trẻ nào quan tâm đến cách mọi thứ hoạt động.'),

	(30, N'LEGO Xe Máy Xúc',1290000,'mayxuc.jpg','mayxuc1.jpg','mayxuc2.jpg','mayxuc3.jpg','LG','2022/11/06',N'LEGO TECHNIC Xe Máy Xúc Hạng Nặng',N'Đồ chơi LEGO Technic Xe Máy Xúc Hạng Nặng 2 trong 1 này có nhiều tính năng cho 
	nhiều giờ chơi sáng tạo. Các bé trai và bé gái từ 8 tuổi trở lên sẽ tận hưởng thử thách lắp ráp nhập vai trước khi khám phá các tính năng thực tế của đồ chơi máy xúc. Xem cách một máy xúc hoạt động 
	Đào, lái, quay; có rất nhiều điều để khám phá trong đồ chơi máy xúc dành cho trẻ em thú vị này. Kiểm tra xẻng di chuyển lên và xuống, cùng với cánh tay di chuyển, đai chạy và thân quay.Ngoài ra còn 
	có một cabin chi tiết với ghế lái và bảng điều khiển. Bộ sản phẩm đi kèm với gạch LEGO bổ sung ‘đá’ để đào và 4 chiếc nón giao thông. Khi đến lúc thử thách mới, mô hình này sẽ được lắp ráp lại thành 
	một chiếc máy kéo có bánh xích. Với cơ chế thực tế, những bộ bổ ích này là một cách tuyệt vời để giới thiệu trẻ em đến thế giới kỹ thuật.'),
	--------------------------------------------------------------------
	(30, N'JUPITIVE GUNDAM HIROTO ',700000,'hiroto.jpg','hiroto1.jpg','hiroto2.jpg','hiroto3.jpg','GD','2022/11/08',N'Đồ chơi mô hình lắp ráp JUPITIVE GUNDAM HIROTO tỉ lệ 1/144',N'Đồ chơi lắp ráp Gunpla HGBD:R 1/144 JUPITIVE GUNDAM HIROTO MOBILE 
	SUIT với 3 mẫu tạo dáng khác nhau trên cùng 1 sản phẩm, đi kèm nhiều phụ kiện chiến đấu thời thượng sẽ là sự bổ sung hoàn hảo cho bộ sưu tầm của bạn'),

	(30, N'GUNDAM SELTSAM MASKED',750000,'selstam.jpg','selstam1.jpg','selstam2.jpg','selstam3.jpg','GD','2022/11/08',N'Đồ chơi mô hình lắp ráp GUNDAM SELTSAM MASKED tỉ lệ 1/144',N'Đồ chơi lắp ráp Gunpla HGBD:R 1/144 GUNDAM SELTSAM MASKED MANS MOBILE 
	SUIT với lớp sơn toát lên vẻ mạnh mẽ, ngầu cùng nhiều tư thế chiến đấu dũng mãnh; hứa hẹn là sự bổ sung xứng đáng vào bộ sưu tập cua bạn'),

	(30, N'V-ZEON GUNDAM CAPTAIN',1500000,'zeon.jpg','zeon1.jpg','zeon2.jpg','zeon3.jpg','GD','2022/11/08',N'Đồ chơi mô hình lắp ráp V-ZEON GUNDAM CAPTAIN tỉ lệ 1/144',N'Đồ chơi lắp ráp Gunpla HGBD:R 1/144 V-ZEON GUNDAM CAPTAIN ZEON với lớp
	sơn đỏ rực mạnh mẽ, kết hợp với trang bị chiến đấu cực kì độc đáo với nhiều kiểu tạo dáng khác nhau; hứa hẹn sẽ là sự bổ sung hoàn hảo cho bộ sưu tầm của bạn'),

	(30, N'ELDORA BRUTE UNKNOWN',700000,'eldora.jpg','eldora1.jpg','eldora2.jpg','eldora3.jpg','GD','2022/11/08',N'Đồ Chơi Siêu Robot Lắp Ráp ELDORA BRUTE tỉ lệ 1/144',N'Siêu robot lắp ráp Gunpla HGBD:R 1/144 Eldora Brute  
	đến từ thương hiệu Gundam mô phỏng một tên quái vật khổng lồ có bốn chân và được trang bị vũ khí bốn lưỡi cùng súng phóng tia trên lưng hết sức nguy hiểm. Hắn có thể càn quét mọi thứ xung quanh chỉ trong 
	nháy mắt. Đồ chơi siêu robot lắp ráp Gundam chắc chắn sẽ mang đến cho bé những phút giây vui chơi thú vị và phát huy trí tưởng tượng phong phú của mình.'),

	(30, N'EARTHREE GUNDAM',950000,'ghiroto.jpg','ghiroto1.jpg','ghiroto2.jpg','ghiroto3.jpg','GD','2022/11/08',N'Đồ chơi mô hình lắp ráp EARTHREE GUNDAM tỉ lệ 1/144',N'Đồ chơi lắp ráp Gunpla kèm phụ kiện GUNDAM HG 1/144 EARTHREE HIROTO 
	là hình mẫu chiến binh dũng mãnh, có thể chiến đấu ở mọi loại địa hình khác nhau và được tích hợp nhiều phụ kiện hỗ trợ hứa hẹn sẽ đóng góp cho bộ sưu tầm của bạn thật mỹ mãn'),
	-----------------------------------------------------------------------
	(30, N'Mô hình Venom chibi',500000,'venom.jpg','venom.jpg','venom.jpg','venom.jpg','CB','2022/11/07',N'Mô hình nhân vật venom marvel thu nhỏ',N'Nhân vật Venom từ truyện tranh mavel đã được chuyển thành mô hình chibi 
	có những khớp nối giúp dễ dàng thay đổi tư thế dễ dàng trưng bày.'),

	(30, N'Mô hình Venom chibi new',500000,'venomnew.jpg','venomnew1.jpg','venomnew2.jpg','venomnew.jpg','CB','2022/11/07',N'Mô hình nhân vật venom marvel thu nhỏ',N'Nhân vật Venom từ truyện tranh mavel đã được chuyển thành mô hình chibi 
	có những khớp nối giúp dễ dàng thay đổi tư thế dễ dàng trưng bày.'),

	(30, N'Mô hình Demon Slayer chibi',500000,'demon.jpg','demon1.jpg','demon2.jpg','demon3.jpg','CB','2022/11/07',N'Mô Hình Nhân Vật Anime Demon Slayer Gojou Chất Lượng Cao',N'Mô Hình Nhân Vật Anime Demon Slayer Gojou Chất Lượng Cao.'),

	(30, N'Mô hình Naruto chibi',500000,'nar.jpg','nar1.jpg','nar2.jpg','nar3.jpg','CB','2022/11/07',N'Mô Hình Nhân Vật Anime Naruto cospaly pikachu',N'Mô hình naruto cos Pikachu cute dễ thương - ZIN DECOR'),

	(30, N'Mô hình anime chibi',500000,'thanhguomdietquy.jpg','thanhguomdietquy1.jpg','thanhguomdietquy2.jpg','thanhguomdietquy3.jpg','CB','2022/11/07',N'Mô hình anime Kimetsu no Yaiba chibi ',N'combo 6 mô hình của anime thanh gươm diệt quỷ.
	Combo bao gồm: Tanjiro Kamado, Nezuko Kamado, Giyu Tomioka, Kanao Tsuyuri và Muzan Kibutsuji.Sản phẩm duy nhất có dán nhãn Bluefin chính thức đã được kiểm tra kỹ lưỡng về độ an toàn và đáp ứng tất cả các quy định về an
	toàn sản phẩm tiêu dùng của Bắc Mỹ và cho phép người mua hỗ trợ sản phẩm'),

	(30, N'Mô hình Vegeta chibi',500000,'vegeta.jpg','vegeta1.jpg','vegeta2.jpg','vegeta3.jpg','CB','2022/11/07',N'Mô hình GK Vegeta chibi chất liệu nhựa PVC,kích thước 4 Inch',N'Được thiết kế và phát triển bởi studio Suruima, mô hình Vegeta chibi được đảm bảo về chất lượng, màu sơn,
	tư thế nhằm đảm bảo thoả mãn những khách hàng khó tính'),
	---------------------------------------------------------------------
	(30, N'Mô hình Ironman',640000,'ironman.jpg','ironman1.jpg','ironman2.jpg','ironman3.jpg','FG','2022/11/05',N'Siêu anh hùng Iron Man tích hợp cánh bay siêu cấp',N'Giới thiệu sản phẩm: Đồ chơi Siêu anh hùng Iron Man tích hợp cánh bay siêu cấp 1 Siêu anh hùng Iron man 1 Hệ thống bay siêu cấp 
	phía sau lưng 1 Hướng dẫn sử dụng Tính năng: Thỏa niềm đam mê với mô hình mô phỏng nhân vật Siêu anh hùng Iron man trừ gian diệt bạo chính xác theo nguyên mẫu nhân vật trong phim Avengers. Đặc tính : Kích thích trí tưởng tượng và sự sáng tạo 
	của bé. Chất liệu: Nhựa cao cấp Kích thước, độ tuổi: Độ tuổi: 5+'),

	(30, N'Mô hình Manjiro Sano',560000,'sano.jpg','sano1.jpg','sano2.jpg','sano3.jpg','FG','2022/11/05',N'Mô hình nhân vật Mikey Tokyo Revenger kích thước 14.8cm',N'Được thiết kế tỉ mỉ và chất liệu an toàn đối với sức khoẻ cùng với những khớp nối có thể di chuyển, mô hình này sẽ đảm bảo
	chinh phục được những người hâm mộ bộ phim Tokyo Revenger và nhân vật Mikey'),

	(30, N'Mô hình Dragonball',1000000,'dr.jpg','dr1.jpg','dr2.jpg','dr3.jpg','FG','2022/11/05',N'Mô hình nhân vật Goku vs Vegeta chiến đấu',N'Mô Hình Dragon Ball Super 2 Goku vs Demonized Vegeta có đèn led rất thích hợp để trang trí góc làm việc, phòng ngủ ,..'),

	(30, N'Mô hình Hulkbuster',1000000,'hulk.jpg','hulk1.jpg','hulk2.jpg','hulk3.jpg','FG','2022/11/05',N'Mô hình nhân vật Hulkbuster siêu đẹp',N'Mô hình chính hãng Comicave: Iron man Mk44 - Hulkbuster rất thích hợp để trang trí góc làm việc, phòng ngủ ,..'),
	--------------------------------------------------------------------
	(30, N'Đế trưng bày',150000,'detrungbay.jpg','detrungbay1.jpg','detrungbay2.jpg','detrungbay.jpg','ANT','2022/11/05',N'Đế trưng bày xe mô hình các tỷ lệ bằng nhựa cứng',N'Đế ô tô 1:32: 20cm x 10cm x 2.5cm, có kèm vít để cố định xe vào đế'),

	(30, N'Đế xoay trưng bày',200000,'dexoay.jpg','dexoay1.jpg','dexoay2.jpg','dexoay.jpg','ANT','2022/11/05',N'ĐỂ XOAY TRƯNG BÀY ĐƯỜNG KÍNH 10CM',N' Đế xoay trưng bày đường kính 10cm sở hữu thiết kế nhỏ gọn và tinh tế. Với mặt kính đặt vật trưng bày dạng tròn bằng chất liệu mica 
	trong suốt có đường kính 10cm. Kết hợp hài hòa cùng phần chân dạng hình tháp vuông bằng chất liệu nhựa. Phần chân đế xoay có 2 màu sắc để bạn có thể lựa chọn.Đế xoay trưng bày lấy nguồn năng lượng từ pin tiểu và công nghệ hấp thụ 
	năng lượng ánh sáng (4 mặt kính ở 4 phía của chân đế). Điều này giúp phụ kiện đế xoay trưng bày có khả năng hoạt động liên tục trong khoảng thời gian dài.'),

	(30, N'Dụng cụ ghép mô hình',56000,'dungcu.jpg','dungcu1.jpg','dungcu2.jpg','dungcu.jpg','ANT','2022/11/05',N'Bộ dụng cụ cắt lắp ghép và uốn mô hình thép hãng Piececool',N'Bộ dụng cụ cắt lắp ghép và uốn mô hình thép hãng Piececool được làm bằng thép không gỉ chất lượng cao tạo nên 
	sự chắc chắn và bền bỉ. Sở hữu thiết kế nhỏ gọn và tinh tế, thuận tiện cho việc di chuyển. Đây là sản phẩm chuyên dụng cho bộ môn lắp ghép mô hình thép, ngoài ra bạn cũng có thể sử dụng trong các loại mô hình kit và mô hình tĩnh')

insert Statuss (Id, [Status]) values
	(N'Chờ xác nhận',N'Chờ xác nhận'),
	(N'Đã xác nhận',N'Đã xác nhận'),
	(N'Đang giao hàng',N'Đang giao hàng'),
	(N'Giao thành công',N'Giao thành công'),
	(N'Đã huỷ đơn hàng',N'Đã huỷ đơn hàng')
/** thêm dữ liệu chỗ này**/
insert Orders (Username, Createdate, [Address],[Status], Paymentstatus) values
	('dung123','2022/03/03',N'Q.12, TP.HCM',N'Chờ xác nhận', N'Đã Thanh Toán'),
	('lan123','2022/10/03',N'Q.1, TP.HCM',N'Đã xác nhận', N'Đã Thanh toán'),
	('tai123','2022/02/03',N'Q.5, TP.HCM',N'Giao thành công', N'Chưa thanh toán'),
	('ADMIN','2022/02/03',N'Q.5, TP.HCM',N'Đang giao hàng', N'Đã Thanh toán'),
	('STAFF5','2022/07/03',N'Q.BT, TP.HCM',N'Đã huỷ đơn hàng', N'Chưa thanh toán')

insert Order_Detail (Orderid, Productid, Price,	Quantity) values
	(1, 1, 1400, 2),
	(2, 18, 1400, 2),
	(3, 21, 1400, 2),
	(4, 4, 1400, 2),
	(5, 25, 2100, 3)

insert Reviews (Username, Review, CreateDate) values
	(N'lan123',N'Trang web dễ dàng sử dụng','2022/12/10'),
	(N'STAFF5',N'Sản phẩm đẹp, chất lượng tốt','2022/12/12'),
	(N'tai123',N'Good','2022/12/14'),
	(N'dung123',N'Sản phẩm tốt, tôi sẽ quay lại mua hàng','2022/12/15')

insert Product_Reviews (Username, Productid, Review, CreateDate) values
	(N'ADMIN', 1, N'sản phẩm đẹp', '2022/12/11'),
	(N'STAFF5', 1, N'Good', '2022/12/10'),
	(N'tai123', 1, N'Lần sau tôi sẽ quay lại', '2022/12/09'),
	(N'STAFF5', 2, N'OK', '2022/12/13')


insert Activities(CreateDate, Activity) values
	('2022/09/01', N'ADMIN vừa chỉnh sửa đơn hàng 1'),
	('2022/01/01', N'STAFF1 vừa chỉnh sửa sản phẩm Xe Máy Xúc Hạng Nặng'),
	('2022/02/01', N'STAFF2 vừa chỉnh sửa sản phẩm Xe Máy Xúc Hạng Nặng'),
	('2022/01/01', N'ADMIN vừa cập nhật nhân viên STAFF2'),
	('2022/01/06', N'STAFF3 vừa chỉnh sửa sản phẩm Xe Máy Xúc Hạng Nặng')


insert Favorites (Username, Productid, Have) values
		(N'ADMIN', 1, 'true'),
		(N'ADMIN', 5, 'true'),
		(N'ADMIN', 3, 'true'),
		(N'STAFF1', 1, 'true'),
		(N'STAFF1', 2, 'true')







------------------------------------------------------------------------


/*	UPDATE o set o.Status = N'Đã huỷ đơn hàng' 
	From Orders o INNER JOIN Order_Detail od on od.Orderid = o.id
	where od.Orderid=10

	select * from Orders

	select * from Order_Detail od INNER JOIN Orders o on od.Orderid = o.id

	select * from Products
	select * from Order_Detail where Orderid = 1

	UPDATE p set p.Quantity = p.Quantity - o.Quantity
	from Products p INNER JOIN Order_Detail o on p.id = o.Productid 
	Where Orderid = 1

	UPDATE p set p.Quantity = p.Quantity + o.Quantity
	from Products p INNER JOIN Order_Detail o on p.id = o.Productid 
	Where Orderid = 1

	
	select p.Name, sum(o.price*o.quantity) as doanhthu 
	from Order_Detail o inner join Products p on p.id = o.Productid 
	group by p.id, p.name order by doanhthu desc

	select c.Name, sum(o.price*o.quantity) as doanhthu 
	from Categories c inner join Products p on c.id = p.Categoryid 
	inner join Order_Detail o on p.id = o.Productid
	group by c.Name order by c.Name

	select c.Name, count(p.Name) as doanhthu 
	from Categories c inner join Products p on c.id = p.Categoryid 
	group by c.Name order by c.Name

	delete from Products where id = 1
	
	select * from Product_Reviews */
	
