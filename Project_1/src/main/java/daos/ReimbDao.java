package daos;
import java.util.List;

import models.User;
import models.Reimbursement;

public interface ReimbDao {
	public long createReimb(long requestor, long supervisor );
	public long updateReimb(long rid);
	public long deleteReimb();
}
