package Main.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Main.entity.Translation;

@Repository
public interface TransactionDAO extends JpaRepository<Translation, Long>{

}
