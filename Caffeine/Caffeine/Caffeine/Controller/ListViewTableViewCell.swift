//
//  ListVIewTableViewCell.swift
//  Caffeine
//
//  Created by hyogang on 2021/08/22.
//

import UIKit

class ListViewTableViewCell: UITableViewCell {

    @IBOutlet weak var lblName: UILabel!
    @IBOutlet weak var lblMg: UILabel!
    @IBOutlet weak var lblMemo: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
