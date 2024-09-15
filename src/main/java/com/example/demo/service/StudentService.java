package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentrepo;
	
	@Autowired
	private EntityManager em;
	
	public List<Student>getAllStudents(){
		
		return studentrepo.findAll();
	}
	
	public Student addStudent(Student student) {
		return studentrepo.save(student);
	}
	
	public List<Student> findStudentByCriteria(String studentName){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery query = cb.createQuery(Student.class);
		Root<Student> student = query.from(Student.class);

		//Predicate
		List<Predicate> predicates = new ArrayList();
		predicates.add(cb.equal(student.get("studentName"), studentName));
		
		Predicate[] pre = predicates.toArray((new Predicate[predicates.size()]));
		query.select(student).where(cb.and(pre));
		
		//Order
		List<Order> orderList = new ArrayList();
		orderList.add(cb.asc(student.get("id")));
		query.orderBy(orderList);
		
		
		
		
		return em.createQuery(query).getResultList();
		
		
	}

}
