package hello.core.order

class Order(
    var memberId: Long,
    var itemName: String,
    var itemPrice: Int,
    var discountPrice: Int,
) {
    fun calculatePrice() = this.itemPrice - discountPrice

    override fun toString(): String {
        return "Order(memberId=$memberId, itemName='$itemName', itemPrice=$itemPrice, discountPrice=$discountPrice)"
    }
}
