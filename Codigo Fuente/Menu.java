import javax.swing.*;
import java.awt.event.*;
import java.awt.Image;
import java.awt.Color;
public class Menu extends JFrame{
	private JLabel etiqueta1;
	private JButton opBinomial,opGeometrica,opHipergeometrica,opPoisson,opAproximacion,opNo;
	public Menu(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setTitle("Menu");
		getContentPane().setBackground(new Color(100,177,255));
		setResizable(false);
		iniciaComponentes();
	}
	public void iniciaComponentes(){
		int c=191;
		etiqueta1 = new JLabel((char)c+"Cual v.a. discreta famosa deseas utilizar?\n");
		etiqueta1.setBounds(55,10,250,30);
		add(etiqueta1);
		opBinomial = new JButton("Binomial");
		opBinomial.setBounds(75,40,215,65);
		add(opBinomial);
		opBinomial.setHorizontalAlignment(SwingConstants.LEFT);
		opBinomial.setBackground(new Color(255,128,0));
		eventoBinomial();
		iconosBinomial();
		opGeometrica = new JButton("Geometrica");
		opGeometrica.setBounds(75,105,215,65);
		add(opGeometrica);
		opGeometrica.setHorizontalAlignment(SwingConstants.LEFT);
		opGeometrica.setBackground(new Color(255,255,128));
		eventoGeometrica();
		iconosGeometrica();
		opHipergeometrica = new JButton("Hipergeometrica");
		opHipergeometrica.setBounds(75,170,215,65);
		add(opHipergeometrica);
		opHipergeometrica.setHorizontalAlignment(SwingConstants.LEFT);
		opHipergeometrica.setBackground(new Color(0,255,255));
		eventoHipergeometrica();
		iconosHipergeometrica();
		opPoisson = new JButton("Poisson");
		opPoisson.setBounds(75,235,215,65);
		add(opPoisson);
		opPoisson.setHorizontalAlignment(SwingConstants.LEFT);
		opPoisson.setBackground(new Color(13,237,7));
		eventoPoisson();
		iconosPossion();
		opAproximacion = new JButton("Aproximacion");
		opAproximacion.setBounds(75,300,215,65);
		add(opAproximacion);
		opAproximacion.setHorizontalAlignment(SwingConstants.LEFT);
		opAproximacion.setBackground(new Color(255,108,108));
		eventoAproximacion();
		iconosAproximacion();
		opNo = new JButton("No lo se");
		opNo.setBounds(75,365,215,65);
		add(opNo);
		opNo.setHorizontalAlignment(SwingConstants.LEFT);
		opNo.setBackground(new Color(128,0,255));
		eventoNo();
		iconosNo();
	}
	public void eventoBinomial(){
		ActionListener listenerBinomial = new ActionListener(){
			public void actionPerformed(ActionEvent evento){
				setVisible(false);
				Binomial bin = new Binomial();
				bin.setBounds(0,0,400,335);
				bin.setLocationRelativeTo(null);
				bin.setVisible(true);
			}
		};
		opBinomial.addActionListener(listenerBinomial);
	}
	public void eventoGeometrica(){
		ActionListener listenerGeometrica = new ActionListener(){
			public void actionPerformed(ActionEvent evento){
				setVisible(false);
				Geometrica gen = new Geometrica();
				gen.setBounds(0,0,400,335);
				gen.setLocationRelativeTo(null);
				gen.setVisible(true);
			}
		};
		opGeometrica.addActionListener(listenerGeometrica);
	}
	public void eventoHipergeometrica(){
		ActionListener listenerHipergeometrica = new ActionListener(){
			public void actionPerformed(ActionEvent evento){
				setVisible(false);
				Hipergeometrica gen = new Hipergeometrica();
				gen.setBounds(0,0,400,335);
				gen.setLocationRelativeTo(null);
				gen.setVisible(true);
			}
		};
		opHipergeometrica.addActionListener(listenerHipergeometrica);
	}
	public void eventoPoisson(){
		ActionListener listenerPoisson = new ActionListener(){
			public void actionPerformed(ActionEvent evento){
				setVisible(false);
				Poisson pos = new Poisson();
				pos.setBounds(0,0,400,335);
				pos.setLocationRelativeTo(null);
				pos.setVisible(true);
			}
		};
		opPoisson.addActionListener(listenerPoisson);
	}
	public void eventoAproximacion(){
		ActionListener listenerAproximacion = new ActionListener(){
			public void actionPerformed(ActionEvent evento){
				setVisible(false);
				Aproximacion apr = new Aproximacion();
				apr.setBounds(0,0,400,335);
				apr.setLocationRelativeTo(null);
				apr.setVisible(true);
			}
		};
		opAproximacion.addActionListener(listenerAproximacion);
	}
	public void valida1(){
		int opcion=0,c=191;
		boolean error;
		do{	
			error=false;
			try{
				opcion = Integer.parseInt(JOptionPane.showInputDialog(null,(char)c+"Cual descripcion consideras "+ 
				"mas cercana a lo que necesitas?\n1-Numero de exitos en n ensayos Bernoulli(Aquel con solo posibilidad"+
				"de exito o fracaso)\n2-Numero de ensayos Bernoulli hasta obtener el primer exito\n"+
				"3-Dada una poblacion con r objetos, de los cuales r1 sondel tipo A y r2 del tipo B, se toma una muestra aleatoria\n"+
				"sin reemplazo con n objetos; menor o igual a r, interesando el numero de objetos del "+
				"tipo A en la muestra\n4-Numero total de ocurrencias de un fenomeno en un periodo de tiempo fijo o una "+
			"	region fija de espacio\n5-No lo tengo muy claro\n6-Salir"));
				if(!(opcion>=1&&opcion<=6)){
					JOptionPane.showMessageDialog(null,"Ingrese una opcion valida","Error",JOptionPane.ERROR_MESSAGE);
					error=true;
				}
			}catch(Exception ex){
				JOptionPane.showMessageDialog(null,"Ingrese una opcion valida","Error",JOptionPane.ERROR_MESSAGE);
				error=true;
			}
		}while(error);
		if(opcion==1){
			JOptionPane.showMessageDialog(null,"Intente Binomial","Resultado",JOptionPane.INFORMATION_MESSAGE);
		}else if(opcion==2){
			JOptionPane.showMessageDialog(null,"Intente Geometrica","Resultado",JOptionPane.INFORMATION_MESSAGE);
		}else if(opcion==3){
			JOptionPane.showMessageDialog(null,"Intente Hipergeometrica","Resultado",JOptionPane.INFORMATION_MESSAGE);
		}else if(opcion==4){
			JOptionPane.showMessageDialog(null,"Intente Poisson","Resultado",JOptionPane.INFORMATION_MESSAGE);
		}else if(opcion==5){
			valida2();
		}
	}
	public void valida2(){
		int opcion=0,c=191;
		boolean error;
		do{
			error=false;
			try{
				opcion = Integer.parseInt(JOptionPane.showInputDialog(null,(char)c+"Puedes relacionar tu problema con "+
				"alguno de estos ejemplos?\n1-Numero de llamadas telefonicas que entran en un conmutador en una hora\n"+
				"2-Una urna contiene 5 bolas rojas y 10 azules. Se selecciona al azar (sin reemplazo), n bolas.\n"+
				"Nos interesan las bolas rojas\n3-Se realizara cierto experimento hasta obtener un resultado exitoso\n"+
				"4-Una moneda se lanza 9 veces. Nos interesa el numero de caras\n5-No lo creo\n6-Salir"));
				if(!(opcion>=1&&opcion<=6)){
					JOptionPane.showMessageDialog(null,"Ingrese una opcion valida","Error",JOptionPane.ERROR_MESSAGE);
					error=true;
				}
			}catch(Exception ex){
				JOptionPane.showMessageDialog(null,"Ingrese una opcion valida","Error",JOptionPane.ERROR_MESSAGE);
				error=true;
			}
		}while(error);
		if(opcion==1){
			JOptionPane.showMessageDialog(null,"Intente Poisson","Resultado",JOptionPane.INFORMATION_MESSAGE);
		}else if(opcion==2){
			JOptionPane.showMessageDialog(null,"Intente Hipergeometrica","Resultado",JOptionPane.INFORMATION_MESSAGE);
		}else if(opcion==3){
			JOptionPane.showMessageDialog(null,"Intente Geometrica","Resultado",JOptionPane.INFORMATION_MESSAGE);
		}else if(opcion==1){
			JOptionPane.showMessageDialog(null,"Intente Binomial","Resultado",JOptionPane.INFORMATION_MESSAGE);
		}else if(opcion==5){
			valida3();
		}
	}
	public void valida3(){
		int opcion=0,c=191,m=181;
		boolean error;
		do{
			error=false;
			try{
				opcion = Integer.parseInt(JOptionPane.showInputDialog(null,(char)c+"Con que datos cuentas?\n"+
				"1-Cantidad de ensayos realizados (n)\n2-Probabilidad del evento de interes (p)\n"+
				"3-Una cantidad de ensayos realizados muy grande (N) y una probabilidad de exito muy chica\n4-Numero de objetos del tipo A y B\n"+
				"5-Un termino Lambda\n6-Ninguno"));
				if(!(opcion>=1&&opcion<=6)){
					JOptionPane.showMessageDialog(null,"Ingrese una opcion valida","Error",JOptionPane.ERROR_MESSAGE);
					error=true;
				}
			}catch(Exception ex){
				JOptionPane.showMessageDialog(null,"Ingrese una opcion valida","Error",JOptionPane.ERROR_MESSAGE);
				error=true;
			}
		}while(error);
		if(opcion==1){
			JOptionPane.showMessageDialog(null,"Intente Binomial","Resultado",JOptionPane.INFORMATION_MESSAGE);
		}else if(opcion==2){
			JOptionPane.showMessageDialog(null,"Intente Geometrica","Resultado",JOptionPane.INFORMATION_MESSAGE);
		}else if(opcion==3){
			JOptionPane.showMessageDialog(null,"Intente Aproximacion","Resultado",JOptionPane.INFORMATION_MESSAGE);
		}else if(opcion==4){
			JOptionPane.showMessageDialog(null,"Intente Hipergeometrica","Resultado",JOptionPane.INFORMATION_MESSAGE);
		}else if(opcion==5){
			JOptionPane.showMessageDialog(null,"Intente Poisson","Resultado",JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null,"Revise sus datos de nuevo","Resultado",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public void eventoNo(){
		ActionListener listenerNo = new ActionListener(){
			public void actionPerformed(ActionEvent evento){
				valida1();
			}
		};
		opNo.addActionListener(listenerNo);
	}
	public void iconosBinomial(){
		ImageIcon iBinomial = new ImageIcon("iconos/Binomial.png");
		ImageIcon ipBinomial = new ImageIcon("iconos/presionado.png");
		ImageIcon irBinomial = new ImageIcon("iconos/adelante.png");
		opBinomial.setIcon(new ImageIcon(iBinomial.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH)));
		opBinomial.setPressedIcon(new ImageIcon(ipBinomial.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH)));
		opBinomial.setRolloverIcon(new ImageIcon(irBinomial.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH)));
	}
	public void iconosGeometrica(){
		ImageIcon iGeometrica = new ImageIcon("iconos/Geometrica.png");
		ImageIcon ipGeometrica = new ImageIcon("iconos/presionado.png");
		ImageIcon irGeometrica = new ImageIcon("iconos/adelante.png");
		opGeometrica.setIcon(new ImageIcon(iGeometrica.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH)));
		opGeometrica.setPressedIcon(new ImageIcon(ipGeometrica.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH)));
		opGeometrica.setRolloverIcon(new ImageIcon(irGeometrica.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH)));
	}
	public void iconosHipergeometrica(){
		ImageIcon iHipergeometrica = new ImageIcon("iconos/Hipergeometrica.png");
		ImageIcon ipHipergeometrica = new ImageIcon("iconos/presionado.png");
		ImageIcon irHipergeometrica = new ImageIcon("iconos/adelante.png");
		opHipergeometrica.setIcon(new ImageIcon(iHipergeometrica.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH)));
		opHipergeometrica.setPressedIcon(new ImageIcon(ipHipergeometrica.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH)));
		opHipergeometrica.setRolloverIcon(new ImageIcon(irHipergeometrica.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH)));
	}
	public void iconosPossion(){
		ImageIcon iPossion = new ImageIcon("iconos/Poisson.png");
		ImageIcon ipPossion = new ImageIcon("iconos/presionado.png");
		ImageIcon irPossion = new ImageIcon("iconos/adelante.png");
		opPoisson.setIcon(new ImageIcon(iPossion.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH)));
		opPoisson.setPressedIcon(new ImageIcon(ipPossion.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH)));
		opPoisson.setRolloverIcon(new ImageIcon(irPossion.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH)));
	}
	public void iconosAproximacion(){
		ImageIcon iAproximacion = new ImageIcon("iconos/Aproximacion.png");
		ImageIcon ipAproximacion = new ImageIcon("iconos/presionado.png");
		ImageIcon irAproximacion = new ImageIcon("iconos/adelante.png");
		opAproximacion.setIcon(new ImageIcon(iAproximacion.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH)));
		opAproximacion.setPressedIcon(new ImageIcon(ipAproximacion.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH)));
		opAproximacion.setRolloverIcon(new ImageIcon(irAproximacion.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH)));
	}
	public void iconosNo(){
		ImageIcon iNo = new ImageIcon("iconos/ayuda.png");
		ImageIcon ipNo = new ImageIcon("iconos/presionado.png");
		ImageIcon irNo = new ImageIcon("iconos/adelante.png");
		opNo.setIcon(new ImageIcon(iNo.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH)));
		opNo.setPressedIcon(new ImageIcon(ipNo.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH)));
		opNo.setRolloverIcon(new ImageIcon(irNo.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH)));
	}
}