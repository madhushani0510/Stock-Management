package bo;

import bo.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;

    }

    public enum BOTypes {
        COMPANY,BOUGHT,SOLD,DIVIDEND,RIGHTISSUES,WATCHLIST,LOGIN
    }

    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case COMPANY:
                return new CompanyBOImpl();
            case BOUGHT:
                return new BoughtBOImpl();
            case SOLD:
                return new SoldBOImpl();
            case DIVIDEND:
                return new DividendBOImpl();
            case RIGHTISSUES:
                return new RightissuesBOImpl();
            case WATCHLIST:
                return new WatchlistBOImpl();
            case LOGIN:
                return new LoginBOImpl();

            default:
                return null;

        }

    }
}
