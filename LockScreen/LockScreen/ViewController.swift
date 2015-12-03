//
//  ViewController.swift
//  LockScreen
//
//  Created by David Chu on 10/15/15.
//  Copyright © 2015 David Chu. All rights reserved.
//

import Cocoa

class ViewController: NSViewController {

    @IBOutlet weak var passwordField: NSSecureTextField!
    @IBOutlet weak var enterButton: NSButton!
    @IBOutlet weak var bgImage: NSImageCell!
    @IBOutlet weak var bgImageView: NSImageView!
    @IBOutlet weak var loginPicture: NSImageView!
    @IBOutlet weak var username: NSTextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Do any additional setup after loading the view.
        let screenSize: CGRect = (NSScreen.mainScreen()?.frame)!
        let screenWidth = screenSize.width
        let screenHeight = screenSize.height
        var frame = view.frame
        frame.size = NSMakeSize(screenWidth, screenHeight)
        bgImageView.frame = frame
        
        username.textColor = NSColor.whiteColor()
        
        loginPicture.wantsLayer = true
        loginPicture.layer?.cornerRadius = (loginPicture.frame.size.width / 2)
        loginPicture.layer?.borderWidth = 1.0
        loginPicture.layer?.borderColor = NSColor.whiteColor().CGColor
        loginPicture.layer?.masksToBounds = true
        
        passwordField.wantsLayer = true
//        passwordField.layer?.borderWidth = 0
        passwordField.layer?.cornerRadius = 10
        passwordField.layer?.masksToBounds = true
        //passwordField.backgroundColor = NSColor.lightGrayColor()
        enterButton.target = self
        enterButton.action = "buttonPressed:"
        passwordField.target = self;
        passwordField.action = "buttonPressed:"
    }

    override var representedObject: AnyObject? {
        didSet {
        // Update the view, if already loaded.
        }
    }
    
    func buttonPressed(obj:AnyObject?) {
        print("Button pressed")
        exit(-1)
    }
}

