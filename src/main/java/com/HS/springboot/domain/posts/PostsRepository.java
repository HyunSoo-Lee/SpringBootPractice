package com.HS.springboot.domain.posts;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//Posts 클래스로 DB를 접근하게 해주는 클래스 - DB Layer 접근자 (MyBatis에서는 Dao라고 불리는-)
//Entity 클래스와 PK 타입을 상속시켜주면 CRUD 메소드가 자동으로 생성!
//Repository 임을 선언하는 어노테이션도 필요없지만, Entity 클래스와 반드시 같은 패키지 내에 묶여 있어야 한다.
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

}
