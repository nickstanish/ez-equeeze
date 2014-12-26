package ez_squeeze.game.exceptions;

public class NotEnoughMoneyException extends Exception{

	/**
	 * @author Nick Stanish
	 */
	private static final long serialVersionUID = -1706980524547310200L;
	public double purchaseAmount;
	public double walletAmount;
	public NotEnoughMoneyException(double purchaseAmount, double walletAmount){
		super();
		this.purchaseAmount = purchaseAmount;
		this.walletAmount = walletAmount;
	}
}
