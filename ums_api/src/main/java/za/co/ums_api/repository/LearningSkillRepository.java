package za.co.ums_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.ums_api.models.LearningSkill;

public interface LearningSkillRepository extends JpaRepository<LearningSkill, Integer> {
    String findByName(String name);
}
