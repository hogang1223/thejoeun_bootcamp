//
//  ListView2TableViewCell.swift
//  Caffeine
//
//  Created by hyogang on 2021/08/24.
//

import UIKit

class ListView2TableViewCell: UITableViewCell {

    @IBOutlet weak var lblNameNonMemo: UILabel!
    @IBOutlet weak var lblMgNonMemo: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
