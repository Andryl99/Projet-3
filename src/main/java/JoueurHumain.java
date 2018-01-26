import com.gg.comportement.*;

public class JoueurHumain extends Player{
	public JoueurHumain()	{
		this.play = new ComportementHumain();
		this.answer = new ReponseManuelle();
	}	
}
