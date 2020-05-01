//
//  AppDelegate.swift
//  bai_dialog_ios
//
//  Created by Tom Koptel on 01/05/2020.
//  Copyright © 2020 Tom Koptel. All rights reserved.
//

import UIKit
import shared

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    
    var window: UIWindow?
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        // Override point for customization after application launch.
        KoiniOSKt.doInitKoin()
        return true
    }
}

