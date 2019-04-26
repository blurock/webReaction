// ===================================================================================================================================
//                                           ...... ...
//                                        .,...........                   Permission is hereby granted, free of charge, to any person
//                               . .:::,,::,. .... .                      obtaining a copy of this software and associated documentation
//                         .     ,;:,..,::,. . ...                        files (the "Software"), to deal in the Software without
//                      .,=iiiitt: . ,::,,. ...                           restriction, including without limitation the rights to
//                  .=tR#WY;,tMYI#R=;:,. .,..,,,,,,,,,.,,,,....           use, copy, modify, merge, publish, distribute, sublicense,
//             ;=,  X#I,t##V+iM#WBi;:...;=:;,.....,.,     .......         and/or sell copies of the Software, and to permit persons
//           ;tVWBXVMMVItB#MBM##X++i+++t;.      ...,,.,...     ....       to whom the Software is furnished to do so, subject to the
//          .IVYWVY:tYMXYRW##WBt;;+i==++;:=;==;:,,......      . ...       following conditions:
//          ,XYR#YI.+YBWMWWBY=   ,.   ,,:;+==;:   .   ...........
//           tR##YIYVYVWWWXi,;+++++=++i+i+++++++= ,,,,.                   1) The copyright notice and this permission notice shall
//           ,BV##IYYIRWMBBW##W#WM##BBBWBRBBBM##B,                           be included in all copies or substantial portions of the
//            i==VMMBBRWMBWWYYWWWXIYBMWi+BMItXXIVX                           Software.
//          . =Y,iYXWX.MBM#XiXWWXiYtBWYiVWMBtYRtIB;
//         . .XBY=tBMtYBRRMI+BMt=YYiYBXMt,;YYiRBMB:                       THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
//          .iR.+XXX=;RRRVi+IXXXBY.tYWM;    ;+ItIi                        EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
//        . :MX  Y+  YY.;RXXR;,IX  III,     ,i+,                          OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
//       . +BX, ;V  :V+  .tX=     ,Iit       I+i                          NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
//     :YYIXi  .;+: tY,  .        ,IIt      .III                          HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
//     .:+=.      .,tt .           YXI.      :Rt.                         WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
//                 .II.            :R;       .;Bi                         FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
//                 :II.             +X,         =YI=                      OTHER DEALINGS IN THE SOFTWARE.
//                  IB,              YX.          :XV;=:
//                 :V+                IR;          .,=X;.                 EXPECT BUGS.
//                =XX,                 iXt            ..
//             , ;BX:                   =VI:
//            ,YRB+                     . :VI                             Author: Henrik Springfors (moliate@yahoo.se)
//           .==;;                         ;=                             Copyright 2003
//          .  .                          .  .
// ===================================================================================================================================

/*
 * ILink.java
 *
 * Created on January 30, 2004, 9:41 AM
 */

package blurock.reaction.link;

/**
 * ILink is a basic command execution interface. It is used to initiate native processes or connect to webservices.
 *
 * @author  moliate
 */
public interface ILink { 
    /**
     * Sets the command to execute, but does not start execution
     * @param command the command to execute. 
     * @author moliate
     */
    public void setCommand(String command);
    /**
     * Sets the execution parameters
     * @param parameters the parameters for the command. Depends on implementation, but is often converted to a java.lang.String.
     * @author moliate
     */
    public void setParameters(Object[] parameters);
    /**
     * Starts execution of previously set command
     * @return 'true' indicates, but does not guarantee success
     * @author moliate
     */
    public boolean start();
    /**
     * Stops execution of a started command. 
     * @return 'true' indicates, but does not guarantee success
     * @author moliate
     */
    public boolean stop();
    /**
     * Set a property to a value. The allowed values are implementation specific. 
     * @return previously set value   
     * @author moliate
     */
    public String setProperty(String property, String value);
    /**
     * Get command output string
     * @return the output of a started command. Often what is printed to stdout
     * @author moliate
     */
    public String getResult();
    /**
     * Get command error string
     * @return Often what is printed to stderr
     * @author moliate
     */
    public String getError();
}
