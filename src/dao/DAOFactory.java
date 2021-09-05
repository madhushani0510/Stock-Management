package dao;

import dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getIntance() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOTypes {
        COMPANY,BOUGHT,SOLD,DIVIDEND,RIGHTISSUES,WATCHLIST,LOGIN
    }

    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case COMPANY:
                return new CompanyDAOImpl();
            case BOUGHT:
                return new BoughtDAOImpl();
            case SOLD:
                return new SoldDAOImpl();
            case DIVIDEND:
                return new DividendDAOImpl();
            case RIGHTISSUES:
                return new RightIssueDAOImpl();
            case WATCHLIST:
                return new WatchlistDAOImpl();
            case LOGIN:
                return new LoginDAOImpl();

            default:
                return null;

        }
    }
}
