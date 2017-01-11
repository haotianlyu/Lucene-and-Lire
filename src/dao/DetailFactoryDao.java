package dao;

import com.Bean.filmBean;

public interface DetailFactoryDao extends FactoryDao{
	public filmBean findOne(int id);
}
