import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.Image;
import java.awt.Color;
import java.math.BigDecimal;
public class Aproximacion extends JFrame{
	private JCheckBox Puntual,Generadora,Esperanza,Varianza,Acumulada;
	private JLabel etiqueta1,etiqueta2,etiqueta3,etiqueta4;
	private JButton calcular,regresar;
	private JTextField caja1,caja2,caja3;
	private JComboBox<String> opAcumulada;
	public Aproximacion(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setTitle("Aproximacion");
		setResizable(false);
		iniciaComponentes();
	}
	public void iniciaComponentes(){
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
		caja1.setForeground(new Color(64,0,128));
		caja2 = new JTextField();
		caja2.setBounds(115,40,75,30);
		add(caja2);
		caja2.setForeground(new Color(64,0,128));
		caja3 = new JTextField();
		caja3.setBounds(205,40,75,30);
		add(caja3);
		caja3.setForeground(new Color(64,0,128));
		Puntual = new JCheckBox("Probabilidad Puntual");
		Puntual.setBounds(10,70,150,30);
		add(Puntual);
		Acumulada = new JCheckBox("Probabilidad Acumulada");
		Acumulada.setBounds(10,100,200,30);
		add(Acumulada);
		eventoAcumulada();
		opAcumulada = new JComboBox<>();
		opAcumulada.setBounds(210,100,100,30);
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
	public BigDecimal calculaPuntual(double L,int x){
		return Poisson.calculaPuntual(L,x);
	}
	public BigDecimal acumuladaMenorI(double L,int x){
		return Poisson.acumuladaMenorI(L,x);
	}
	public BigDecimal calculaAcumulada(double L,int x){
		BigDecimal resultado=BigDecimal.ZERO;
		BigDecimal aux=BigDecimal.ONE;
		if(opAcumulada.getSelectedItem().equals("X>=x")){
			return aux.subtract(acumuladaMenorI(L,x-1));
		}else if(opAcumulada.getSelectedItem().equals("X>x")){
			return aux.subtract(acumuladaMenorI(L,x));
		}else if(opAcumulada.getSelectedItem().equals("X<=x")){
				return acumuladaMenorI(L,x);
		}else if(opAcumulada.getSelectedItem().equals("X<x")){
				return acumuladaMenorI(L,x).subtract(calculaPuntual(L,x));
		}else return resultado;
	}
	public void eventoCalcula(){
		ActionListener listener = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ev){
				double esperanza,varianza;
				BigDecimal puntual,acumulada;
				String generadora,resultados="Poisson:\n";
				if(Puntual.isSelected()){
					puntual=calculaPuntual(Integer.parseInt(caja1.getText())*Double.parseDouble(caja2.getText()),Integer.parseInt(caja3.getText()));
					resultados+="Puntual:"+puntual+"\n";
				}
				if(Acumulada.isSelected()){
					acumulada=calculaAcumulada(Integer.parseInt(caja1.getText())*Double.parseDouble(caja2.getText()),Integer.parseInt(caja3.getText()));
					resultados+="Acumulada:"+acumulada+"\n";
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