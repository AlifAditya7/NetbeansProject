/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kemahasiswaan;
import java.io.FileInputStream;
import java.util.Properties;
import javax.swing.JOptionPane;
/**
 *
 * @author USER
 */
public class koneksi {
    public Properties mypanel, myLanguage;
    public String strNamePanel;
    public koneksi()
    {

    }
    public String SettingPanel(String nmPanel)
    {
        try {
            mypanel = new Properties();
            mypanel.load(new FileInputStream("lib/database.ini"));
            strNamePanel = mypanel.getProperty(nmPanel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
            System.err.println(e.getMessage());
            System.exit(0);
        }
        return strNamePanel;
    }
}
