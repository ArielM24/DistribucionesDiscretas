import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.Image;
import java.awt.Color;
import java.math.BigDecimal;
import java.math.MathContext;
public class Binomial extends JFrame{
	private JCheckBox Puntual,Generadora,Esperanza,Varianza,Acumulada;
	private JLabel etiqueta1,etiqueta2,etiqueta3,etiqueta4;
	private JButton calcular,regresar;
	private JTextField caja1,caja2,caja3;
	private JComboBox<String> opAcumulada;
	private static Calculos C;
	public Binomial(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setTitle("Binomial");
		setResizable(false);
		iniciaComponentes();
	}
	public void iniciaComponentes(){
		C = new Calculos();
		int c=191;
		etiqueta1 = new JLabel((char)c+"Que deseas saber (ingresa parametros)?");
		etiqueta1.setBounds(10,10,300,30);
		add(etiqueta1);
		etiqueta2 = new JLabel("n:");
		etiqueta2.setBounds(10,40,30,30);
		add(etiqueta2);
		etiqueta3 = new JLabel("p:");
		etiqueta3.setBounds(100,40,30,30);
		add(etiqueta3);
		etiqueta4 = new JLabel("x:");
		etiqueta4.setBounds(190,40,30,30);
		add(etiqueta4);
		caja1 = new JTextField();
		caja1.setBounds(25,40,75,30);
		add(caja1);
		caja1.setForeground(new Color(255,128,0));
		caja2 = new JTextField();
		caja2.setBounds(115,40,75,30);
		add(caja2);
		caja2.setForeground(new Color(255,128,0));
		caja3 = new JTextField();
		caja3.setBounds(205,40,75,30);
		add(caja3);
		caja3.setForeground(new Color(255,128,0));
		Puntual = new JCheckBox("Probabilidad Puntual");
		Puntual.setBounds(10,70,150,30);
		add(Puntual);
		Generadora = new JCheckBox("Funcion Generadora de Momentos");
		Generadora.setBounds(10,100,250,30);
		add(Generadora);
		Esperanza = new JCheckBox("Esperanza");
		Esperanza.setBounds(10,130,100,30);
		add(Esperanza);
		Varianza = new JCheckBox("Varianza");
		Varianza.setBounds(10,160,100,30);
		add(Varianza);
		Acumulada = new JCheckBox("Probabilidad Acumulada");
		Acumulada.setBounds(10,190,200,30);
		add(Acumulada);
		eventoAcumulada();
		opAcumulada = new JComboBox<>();
		opAcumulada.setBounds(210,190,100,30);
		add(opAcumulada);
		opAcumulada.addItem("X<=x");
		opAcumulada.addItem("X<x");
		opAcumulada.addItem("X>=x");
		opAcumulada.addItem("X>x");
		opAcumulada.setEnabled(false);
		calcular = new JButton("Calcular");
		calcular.setBounds(10,230,165,65);
		add(calcular);
		calcular.setHorizontalAlignment(SwingConstants.LEFT);
		calcular.setBackground(new Color(123,145,167));
		eventoCalcula();
		iconosC();
		regresar = new JButton("Regresar");
		regresar.setBounds(195,230,165,65);
		add(regresar);
		regresar.setHorizontalAlignment(SwingConstants.LEFT);
		regresar.setBackground(new Color(123,145,167));
		eventoRegresa();
		iconosR();
	}
	public void eventoAcumulada(){
		ChangeListener listener = new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent ev){
				if(Acumulada.isSelected()==true){
					opAcumulada.setEnabled(true);
				}else{
					opAcumulada.setEnabled(false);
				}
			}
		};
		Acumulada.addChangeListener(listener);
	}
	public static BigDecimal calculaPuntual(int n,double p,int x){
		BigDecimal resultado = BigDecimal.ZERO;
		BigDecimal T1 = new BigDecimal(C.Combinacion(n,x));
		BigDecimal T2 = new BigDecimal(p+"");
		BigDecimal T3 = new BigDecimal((1-p)+"");
		T2=T2.pow(x);
		T3=T3.pow(n-x);
		resultado = T1.multiply(T2.multiply(T3));
		if(resultado.scale()<16)
			return resultado;
		else {
			BigDecimal resultado1 = new BigDecimal(resultado.toString(),MathContext.DECIMAL64);
			return resultado1;
		}
	}
	public String calculaGeneradora(){
		return "(q + pe^t)^n";
	}
	public double calculaEsperanza(int n,double p){
		double resultado=n*p;
		return resultado;
	}
	public double calculaVarianza(int n,double p){
		double resultado=n*p*(1-p);
		return resultado;
	}
	public static BigDecimal acumuladaMenorI(int n,double p,int x){
		BigDecimal resultado=BigDecimal.ZERO,aux;
		int i;
				for(i=0;i<=x;i++){
					aux=calculaPuntual(n,p,i);
					resultado = resultado.add(aux);
				}
		return resultado;
	}
	public BigDecimal calculaAcumulada(int n,double p,int x){
		BigDecimal resultado=BigDecimal.ZERO;
		BigDecimal aux=BigDecimal.ONE;
		if(opAcumulada.getSelectedItem().equals("X>=x")){
			return aux.subtract(acumuladaMenorI(n,p,x-1));
		}else if(opAcumulada.getSelectedItem().equals("X>x")){
			return aux.subtract(acumuladaMenorI(n,p,x));
		}else if(opAcumulada.getSelectedItem().equals("X<=x")){
				return acumuladaMenorI(n,p,x);
		}else if(opAcumulada.getSelectedItem().equals("X<x")){
				return acumuladaMenorI(n,p,x).subtract(calculaPuntual(n,p,x));
		}else return resultado;
	}
	public void eventoCalcula(){
		ActionListener listener = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ev){
				double esperanza,varianza;
				BigDecimal puntual,acumulada;
				String generadora,resultados="";	
				if(Puntual.isSelected()){
					puntual=calculaPuntual(Integer.parseInt(caja1.getText()),Double.parseDouble(caja2.getText()),Integer.parseInt(caja3.getText()));
					resultados+="Puntual:  "+puntual+"\n";
				}
				if(Generadora.isSelected()){
					generadora=calculaGeneradora();
					resultados+="Generadora:  "+generadora+"\n";
				}
				if(Esperanza.isSelected()){
					int m=181;
					esperanza=calculaEsperanza(Integer.parseInt(caja1.getText()),Double.parseDouble(caja2.getText()));
					resultados+="Esperanza "+(char)m+":  "+esperanza+"\n";
				}
				if(Varianza.isSelected()){
					varianza=calculaVarianza(Integer.parseInt(caja1.getText()),Double.parseDouble(caja2.getText()));
					resultados+="Varianza:  "+varianza+"\n";
				}
				if(Acumulada.isSelected()){
					acumulada=calculaAcumulada(Integer.parseInt(caja1.getText()),Double.parseDouble(caja2.getText()),Integer.parseInt(caja3.getText()));
					resultados+="Acumulada:  "+acumulada+"\n";
				}
				JOptionPane.showMessageDialog(null,"resultados:\n"+resultados);
			}
		};
		calcular.addActionListener(listener);
	}
	public void eventoRegresa(){
		ActionListener listener = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ev){
				setVisible(false);
				Menu sa = new Menu();
				sa.setBounds(0,0,365,520);
				sa.setLocationRelativeTo(null);
				sa.setVisible(true);
			}
		};
		regresar.addActionListener(listener);
	}
	public void iconosC(){
		ImageIcon ic = new ImageIcon("iconos/calcular.png");
		ImageIcon ipc = new ImageIcon("iconos/presionado.png");
		ImageIcon irc = new ImageIcon("iconos/adelante.png");
		calcular.setIcon(new ImageIcon(ic.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH)));
		calcular.setPressedIcon(new ImageIcon(ipc.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH)));
		calcular.setRolloverIcon(new ImageIcon(irc.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH)));
	}
	public void iconosR(){
		ImageIcon ib = new ImageIcon("iconos/regresar.png");
		ImageIcon ipb = new ImageIcon("iconos/presionado.png");
		ImageIcon irb = new ImageIcon("iconos/atras.png");
		regresar.setIcon(new ImageIcon(ib.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH)));
		regresar.setPressedIcon(new ImageIcon(ipb.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH)));
		regresar.setRolloverIcon(new ImageIcon(irb.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH)));
	}
}