//
//  EditDelegate.swift
//  Navigation
//
//  Created by hyogang on 2021/07/22.
//
// Protocol : 자바의 interface
protocol EditDelegate {
    func didMessageEditDone(_ controller: EditViewController, message: String)
    func didImageOnOffDone(_ controller: EditViewController, isOn: Bool)
}
