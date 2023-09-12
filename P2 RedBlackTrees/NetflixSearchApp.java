import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class NetflixSearchApp {

	public static void main(String[] args) {

		NetflixDataReaderInterfaceDW dw1 = new NetflixDataReaderDW();

		DatabaseRBTInterfaceAE<NetflixDataInterfaceDW> rbt;
		rbt = new DatabaseRBTAE<>();

		NetflixRBTBackendInterface bd = new NetflixRBTBackendBD(rbt,dw1);

		NetflixFrontendInterfaceFD fd = new NetflixFrontendFD(bd);

		fd.runCommandLoop();
		
	}

}
