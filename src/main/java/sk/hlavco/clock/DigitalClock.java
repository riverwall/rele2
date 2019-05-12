package sk.hlavco.clock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;


    public class DigitalClock {

        public static void main(String[] arguments) {

            final java.util.Timer[] mainMinuteTimer = {null};

            Delegator dlg = new Delegator();

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

//            f.add(input);

            JButton btnStartTime = new JButton("Startuj");
            btnStartTime.addActionListener(new Action() {

                @Override
                public Object getValue(String key) {
                    return null;
                }

                @Override
                public void putValue(String key, Object value) {

                }

                @Override
                public void setEnabled(boolean b) {

                }

                @Override
                public boolean isEnabled() {
                    return false;
                }

                @Override
                public void addPropertyChangeListener(PropertyChangeListener listener) {

                }

                @Override
                public void removePropertyChangeListener(PropertyChangeListener listener) {

                }

                @Override
                public void actionPerformed(ActionEvent e) {
                   mainMinuteTimer[0] = dlg.getProcesy().procesSekundovy();


                }
            });

            f.add(btnStartTime);

            JButton btnStopTime = new JButton("Stopni");
            btnStopTime.addActionListener(new Action() {
                @Override
                public Object getValue(String key) {
                    return null;
                }

                @Override
                public void putValue(String key, Object value) {

                }

                @Override
                public void setEnabled(boolean b) {

                }

                @Override
                public boolean isEnabled() {
                    return false;
                }

                @Override
                public void addPropertyChangeListener(PropertyChangeListener listener) {

                }

                @Override
                public void removePropertyChangeListener(PropertyChangeListener listener) {

                }

                @Override
                public void actionPerformed(ActionEvent e) {
                    mainMinuteTimer[0].cancel();
                }
            });

            f.add(btnStopTime);
            ////
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
//            dlg.getOvladanieRele().stukajRelatkami();
        }
    }



