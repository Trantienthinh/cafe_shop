/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import java.util.List;

/**
 *
 * @author PC
 */
public interface DAOInterface<T> {
    void Them(T t);
    void Sua(T t, Integer id);
    void Xoa(Integer id);
    List<T> LayTT();   
    T TimKiem(String ma);
    boolean kiemTraMa(String ma);
}
