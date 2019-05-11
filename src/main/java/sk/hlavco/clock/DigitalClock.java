package sk.hlavco.clock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;


    public class DigitalClock {

        public static void main(String[] arguments) {

            ClockLabel dateLable = new ClockLabel("date");
            ClockLabel timeLable = new ClockLabel("time");
            ClockLabel dayLable = new ClockLabel("day");

            JFrame.setDefaultLookAndFeelDecorated(true);
            JFrame f = new JFrame("Digital Clock");
            f.setSize(300,150);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setLayout(new GridLayout(4, 1));

            f.add(dateLable);
            f.add(timeLable);
            f.add(dayLable);

            Format shortTime = DateFormat.getTimeInstance(DateFormat.SHORT);
            JFormattedTextField input = new JFormattedTextField(shortTime);
            input.setValue(new Date());
            input.setColumns(20);

            f.add(input);

            f.getContentPane().setBackground(Color.black);

            f.setVisible(true);
        }
    }

    class ClockLabel extends JLabel implements ActionListener {

        Delegator dlg = new Delegator();

        String type;
        SimpleDateFormat sdf;

        public ClockLabel(String type) {
            this.type = type;
            setForeground(Color.green);

            switch (type) {
                case "date" : sdf = new SimpleDateFormat("  MMMM dd yyyy");
                    setFont(new Font("sans-serif", Font.PLAIN, 12));
                    setHorizontalAlignment(SwingConstants.LEFT);
                    break;
                case "time" : sdf = new SimpleDateFormat("hh:mm:ss a");
                    setFont(new Font("sans-serif", Font.PLAIN, 40));
                    setHorizontalAlignment(SwingConstants.CENTER);
                    break;
                case "day"  : sdf = new SimpleDateFormat("EEEE  ");
                    setFont(new Font("sans-serif", Font.PLAIN, 16));
                    setHorizontalAlignment(SwingConstants.RIGHT);
                    break;

                default     : sdf = new SimpleDateFormat();
                    break;
            }

            Timer t = new Timer(1000, this);
            t.start();

        }

        public void actionPerformed(ActionEvent ae) {
            Date d = new Date();
            setText(sdf.format(d));

            System.out.println(sdf.format(d));
            dlg.getOvladanieRele().stukajRelatkami();
        }
    }



