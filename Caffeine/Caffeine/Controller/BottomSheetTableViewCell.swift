//
//  BottomSheetTableViewCell.swift
//  Caffeine
//
//  Created by Jaewon Park on 2021/08/22.
//

import UIKit

class BottomSheetTableViewCell: UITableViewCell {

    @IBOutlet weak var lblItem: UILabel!
    @IBOutlet weak var lblCaffeine: UILabel!
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
