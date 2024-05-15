/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sbenbouzid
 */
public abstract class Serialization {
    public abstract void serialize(HttpServletRequest req, HttpServletResponse res) throws IOException;
    
}
