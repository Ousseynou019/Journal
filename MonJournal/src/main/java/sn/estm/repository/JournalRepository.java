package sn.estm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sn.estm.entites.Journal;

public interface JournalRepository extends JpaRepository<Journal,Long>
{
	   public Journal findByNumber(Long number);
}