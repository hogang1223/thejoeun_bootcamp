//
//  TodayTableViewCell.swift
//  Caffeine
//
//  Created by Jaewon Park on 2021/08/22.
//

import UIKit

class TodayTableViewCell: UITableViewCell {

    @IBOutlet weak var itemImgView: UIImageView!
    @IBOutlet weak var lblItem: UILabel!
    @IBOutlet weak var lblCaffeineMg: UILabel!
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
