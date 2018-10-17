
import com.revature.dao.TransactionsDAO;
import com.revature.dao.TransactionsDAOImpl;

public class Driver {

	public static void main(String[] args) {
		TransactionsDAO td = new TransactionsDAOImpl();
		System.out.println(td.getTransactionByID(124));
	}

}
