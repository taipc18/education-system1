package Main.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Main.entity.Category;


public interface CategoryDAO extends JpaRepository<Category, String>{

	@Query(value = "select sum(od.price*od.quantity) as 'doanhthu' "
			+ "from Categories c inner join Products p on c.id = p.Categoryid inner join Order_Detail od on p.id = od.Productid "
			+ "inner join Orders o on od.Orderid = o.id where o.Status = 'Giao thành công' "
			+ "group by c.name order by c.name ", nativeQuery = true)
	public List<Object> DoanhThuTheoLoai();
	
	
	@Query(value = "select count(p.name) as 'doanhthu' "
			+ "from Categories c inner join Products p on c.id = p.Categoryid "
			+ "group by c.name order by c.name", nativeQuery = true)
	public List<Object> SoLuongTheoLoai();
}
