package daos;

import java.util.List;

import models.Holder;

public interface HolderDao {
	
	public List<Holder> getHolders();
	public Holder getHolderByUsername(String holderUserName);
	public Holder createHolder(Holder H);
	public void addBalance(String name, double deposit);
	public void subtractBalance(String name, double widthdrawl);

}
