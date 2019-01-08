import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.BorderLayout;
import java.util.concurrent.ThreadLocalRandom;


public class Window extends JFrame implements ActionListener, KeyListener {
    ArrayList<GenerationParameter> params = new ArrayList<>();
    ArrayList<TestData> Data = new ArrayList<>();


    public void createWindow() {


        JFrame wFrame = new JFrame("window");
        wFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JDialog wDialog = new JDialog();
        wDialog.setSize(1600, 830);
        wFrame.setSize(1600, 870);
        JTextArea textArea = new JTextArea();
        JTextField textField = new JTextField(10);
        JTextField showCorrectness = new JTextField(10);
        JPanel left = new JPanel();
        JPanel right = new JPanel();
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        JButton buttonStop = new JButton("Reset");
        JButton buttonStart = new JButton("Start");
        JButton buttonSpeichern = new JButton("Speichern");
        JButton buttonParameter = new JButton("Set Parameter");
        JButton buttonAbbrechen = new JButton("Abbrechen");
        JButton buttonBeenden = new JButton ("Beenden");
        JButton buttonHilfe = new JButton("Hilfe");
        JButton buttonOptions = new JButton("Options");
        JCheckBox CheckBoxRandomVariable = new JCheckBox("random");


        //CheckBoxRandomVariable.setMnemonic(KeyEvent.VK_C);
        CheckBoxRandomVariable.setSelected(false);
        textArea.setEditable(false);
        textArea.setLineWrap(false);
        textField.setFocusable(true);
        textField.setEnabled(false);
        showCorrectness.setEnabled(false);
        right.setBackground(Color.gray);
        showCorrectness.setBackground(Color.gray);

        GroupLayout layout = new GroupLayout(right);
        right.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        buttonStart.setEnabled(false);
        buttonSpeichern.setEnabled(true);
        buttonSpeichern.setEnabled(false);




        left.setBackground(Color.white);
        split.setLeftComponent(left);
        split.setRightComponent(right);



        buttonStart.setBounds(75, 10, 100, 30);
        textField.setBounds(75, 55, 100, 20);
        showCorrectness.setBounds(75, 90, 100, 20 );
        buttonParameter.setBounds(65, 300, 120, 30);
        buttonSpeichern.setBounds(75, 340, 100, 30);
        buttonStop.setBounds(75, 450, 100, 30);
        buttonAbbrechen.setBounds(75,  490, 100, 30);
        buttonBeenden.setBounds(75, 600, 100, 30);
        buttonHilfe.setBounds(75, 640, 100, 30);
        buttonOptions.setBounds(75, 680, 100, 30);

        // optionPane.add(CheckBoxRandomVariable);



        Font f1 = new Font(Font.MONOSPACED, Font.PLAIN, 14);


        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(1500,990));
        textArea.setBackground(Color.white);
        textArea.setFont(f1);

        right.add(buttonStart);
        right.add(buttonStop);
        right.add(textField);
        right.add(new JSeparator(JSeparator.HORIZONTAL));
        right.add(buttonParameter);
        right.add(buttonSpeichern);
        right.add(showCorrectness);
        right.add(buttonParameter);
        right.add(buttonAbbrechen);
        right.add(buttonBeenden);
        right.add(buttonHilfe);
        right.add(buttonOptions);


        left.add(scrollPane, BorderLayout.CENTER);

        wFrame.add(split);
        wFrame.setVisible(true);
        split.setDividerLocation(1300);
        split.setResizeWeight(0.85d);


        final ToolTipManager ttm = ToolTipManager.sharedInstance();
        final MouseEvent event = new MouseEvent(this, 0,0,0,0,0,0, false);
        buttonStop.setToolTipText("Resetet alle Parameter und Eingaben");
        buttonAbbrechen.setToolTipText("bricht aktuelle Aufgabe ab");
        buttonParameter.setToolTipText("Datei mit Parametern laden");
        buttonHilfe.setToolTipText("ErklÃ¤rungen zum Programm");
        ttm.setInitialDelay(0);


        buttonOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int input = JOptionPane.showConfirmDialog(null, "Parameter und Daten LÃ¶schen?" + "\n" + "Daten ggf. vorher speichern", "Delete", JOptionPane.YES_NO_OPTION);
                if(input == 0) {
                    Data.clear();
                    params.clear();
                    System.out.println("Deleted");
                    textArea.setText("");
                    showCorrectness.setBackground(Color.gray);
                }
            }

        });
        buttonAbbrechen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long endTime = System.currentTimeMillis();
                if (Data.size() != 0) {
                    textField.setEnabled(true);
                    buttonStart.setEnabled(true);
                    if (Data.size() == 1) {
                        Data.get(0).setEndTime(endTime);
                        Data.get(0).addInput("abgebrochen");
                    } else {
                        Data.get(Data.size() - 1).setEndTime(endTime);
                        Data.get(Data.size() - 1).addInput("abgebrochen");
                    }
                    JOptionPane.showMessageDialog(null,"Aktuelle Aufgabe abgebrochen, zum fortfahren Start drÃ¼cken");


                }

            }

        });

        buttonBeenden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int input = JOptionPane.showConfirmDialog(null, "Beenden?" + "\n" + "Daten vorher speichern!", "Beenden", JOptionPane.YES_NO_OPTION);
                if(input == 0){
                    wFrame.dispose();
                }



            }
        });

        buttonHilfe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Zu erst muss eine Datei mit den Aufgaben Ã¼ber den Set Parameter button geladen werden.\n" +
                        "Danach kann man Ã¼ber den Button Start das Experiment starten.\nDer Pseudcode wird angezeigt und das Eingabefeld wird automatisch fokusiert und die Zeitmessung gestartet \n" +
                        "Die Eingabe erfolgt als ganz zahl. Mit Enter wird die Eingabe bestÃ¤tigt.\nWird das Feld unter dem eingabe Feld rot ist die Eingabe falsch.\n" +
                        "Erst bei richtiger Eingabe kann Ã¼ber den Start button die nÃ¤chste Aufgabe generiert werden \n" +
                        "Nachdem alle Aufgaben abgearbeitet sind erscheint der Dialog keine Parameter. \nDie Daten mÃ¼ssen dann gespeichert werden");



            }
        });

        //Parameter werden eingelesen und generiert
        buttonParameter.addActionListener(new ActionListener() {
            //Format der eigelesenen Datei "Type;Input;VerticalDepth;HorizontalDepth"
            @Override
            public void actionPerformed(ActionEvent e) {
                int lineConstant = 40;
                buttonSpeichern.setEnabled(true);
                EnumModus modus = EnumModus.UPPERCASE;
                final JFileChooser chooser = new JFileChooser();
                int value = chooser.showOpenDialog(Window.this);
                if (value == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    try {
                        BufferedReader reader = new BufferedReader(new java.io.FileReader(file));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            CodeType type;
                            int vDepth;
                            int hDepth;
                            //Uppercase/Lowercase per document
                            /*
                            if(line.startsWith("#")){
                               String mod = line.replaceAll("#", "");
                               System.out.println("line: " + mod);
                               if(mod.contains("upper")){
                                   modus = EnumModus.UPPERCASE;
                                   System.out.println("upper");
                               }else if (mod.contains("lower")){
                                   modus = EnumModus.LOWERCASE;
                                   System.out.println("lower");
                                }
                            }else {
                                String[] part = new String[3];
                                part = line.split(";");
                                vDepth = Integer.parseInt(part[1]);
                                hDepth = Integer.parseInt(part[2]);
                                System.out.println("part 0 " + part[0]);
                                if (part[0].equals("if") || part[0].equals("IF")) {
                                    type = CodeType.IF;
                                } else {
                                    type = CodeType.SWITCH;
                                }*/





                            String[] part = new String[4];
                            part = line.split(";");
                            vDepth = generateVdepth();
                            // Integer.parseInt(part[1]);
                            hDepth = Integer.parseInt(part[1]);
                            if (part[0].equals("if") || part[0].equals("IF")) {
                                type = CodeType.IF;
                            } else {
                                type = CodeType.SWITCH;
                            }
                            if(part[2].contains("u")){
                                modus = EnumModus.UPPERCASE;
                            }else if (part[2].contains("l")) {
                                modus = EnumModus.LOWERCASE;

                            }
                            GenerationParameter param = new GenerationParameter(vDepth, hDepth, type, lineConstant, modus, IdentifierArt.randomIdentifier);
                            System.out.println("type: " + type.toString() + " vdepth " + vDepth + " hDepth " + hDepth + " modus " + modus);
                            params.add(param);


                        }
                    } catch (FileNotFoundException e2) {
                        // TODO Auto-generated catch block
                        e2.printStackTrace();
                    } catch (IOException e2) {
                        // TODO Auto-generated catch block
                        e2.printStackTrace();
                    }

                }
                buttonStart.setEnabled(true);
            }
        });

        //Startbutton
        buttonStart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Start");
                showCorrectness.setBackground(Color.gray);
                showCorrectness.setText("");
                buttonSpeichern.setEnabled(false);
                buttonBeenden.setEnabled(false);
                long startTime = System.currentTimeMillis();
                if(!params.isEmpty()) {
                    CodeGenerator g = new CodeGenerator(params.get(0));
                    String code = g.generateCode();
                    TestData data = new TestData(startTime, code, params.get(0), params.get(0).getModus());
                    buttonStart.setEnabled(false);
                    textField.setEnabled(true);
                    buttonStop.setEnabled(true);
                    textField.requestFocusInWindow();
                    params.remove(0);
                    textArea.setText(code);
                    textArea.setCaretPosition(0);
                    Data.add(data);
                }else{
                    JOptionPane.showMessageDialog(null,"Keine Parameter");
                    buttonStop.setEnabled(true);
                    buttonStart.setEnabled(false);
                    textField.setEnabled(false);
                    buttonSpeichern.setEnabled(true);
                    safe();

                }


            }
        });
        //Wenn Enter gedrÃ¼ckt wurde
        textField.addKeyListener(new KeyListener() {
            public void actionPerformed(ActionEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER) {

                    System.out.println("Enter pressed");
                    long endTime = System.currentTimeMillis();

                    String input = textField.getText();
                    int intInput = Integer.parseInt(input);
                    if(intInput == Data.get(Data.size()-1).getDepth()){
                        showCorrectness.setSelectedTextColor(Color.black);
                        showCorrectness.setBackground(Color.GREEN);
                        buttonStart.setEnabled(true);
                        textField.setEnabled(false);
                        buttonSpeichern.setEnabled(true);
                        buttonBeenden.setEnabled(true);
                        if(Data.size() == 1){
                            Data.get(0).setEndTime(endTime);
                            Data.get(0).addInput(input);
                        }else {
                            Data.get(Data.size()-1).setEndTime(endTime);
                            Data.get(Data.size()-1).addInput(input);
                        }
                    }else{
                        showCorrectness.setBackground(Color.red);
                        Data.get(Data.size()-1).addInput(input);
                    }

                    textField.setText("");
                    System.out.println("input: " + input);




                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub

            }
        });


        buttonSpeichern.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setEnabled(false);
                safe();
                buttonStart.setEnabled(false);
            }

        });

    /*

    public static void main(String[] args){

        Window w = new Window();
        w.createWindow();
    }
    */
    }
    public void safe(){

        final JFileChooser chooser = new JFileChooser();
        int value = chooser.showSaveDialog(Window.this);
        if(value == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            String path = chooser.getSelectedFile().getPath();
            if(!path.toLowerCase().endsWith(".csv")){
                path += ".csv";
                file = new File(path);
            }

            String commaSeperatedString = "";

            for(int i = 0; i < Data.size(); i++) {
                Data.get(i).setCodedModus();
                Data.get(i).setCodedType();
                commaSeperatedString +=  Data.get(i).getType().toString() +  ";" + Data.get(i).getInput() +";" +  Data.get(i).getDuration()+ ";"
                        + Data.get(i).getHorizontalDepth() + ";" + Data.get(i).getVerticalDepth() + ";" + Data.get(i).getModus() + ";" + Data.get(i).getTypeCoded() + ";" +  Data.get(i).getModusCoded() +  "\n";
            }
            try {
                String colLabels = "sep=;\ntype;input;durration;Hdepth;Vdepth;Modus;typeEncoded;modusEncoded\n";
                PrintWriter pw = new PrintWriter(file);
                BufferedWriter out = new BufferedWriter(pw);
                out.write(colLabels);
                out.write(commaSeperatedString);
                out.close();
            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }




    }



    private int generateVdepth(){
        int vDepth = ThreadLocalRandom.current().nextInt(2,5);
        System.out.println("vdepth " + vDepth);
        return vDepth;
    }




    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}}