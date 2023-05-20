//
//  LampDelegate.swift
//  Quiz15_NavigationControl
//
//  Created by hyogang on 2021/07/22.
//

protocol LampDelegate {
    func didPinkLampOnOffDone(_ controller: LampViewController, pinkLampOn: Bool)
    func didLampOnOffDone(_ controller: LampViewController, lampIsOn: Bool)
}
