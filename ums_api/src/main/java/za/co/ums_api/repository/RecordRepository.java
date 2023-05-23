package za.co.ums_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.ums_api.models.Records;

public interface RecordRepository extends JpaRepository<Records, Integer>
{

}
