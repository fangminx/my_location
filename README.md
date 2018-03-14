## 根据供应商名称和关键字来查找标准地址

### URL
http://localhost:9999/my_location/api/location/search
### Method
GET
### Request Header
| **名称** | **必填** | **示例** |
| ----- | ----- | -- |
| language | true | zh_cn/en-us |
| country | false | china/foreign/all 默认all |



### Request Parameter
| **参数名** | **传入形式** | **类型** | **必填** | **备注** |
| -- | -- | -- | -- | -- | -- |
| vendorType | query string| String | false | 默认standard |
| keyWord | query string| String | true | string字符 |
| page | query string | int | false | 当前页数。第一页为0 |
| size | query string | int | false | 每页数量。 |
| type | query string | string | false | 选填城市类型如city，state等，不填查全部。 |

### Response Body
``` json
[
    {
        "code": "CHN022006000",
        "language": "zh_cn",
        "type": "CITY",
        "country": "中国",
        "state": "吉林",
        "countryCode": "CHN",
        "stateCode": "22",
        "cityCode": "006",
        "city": "白山",
        "districtCode": "0",
        "district": "白山",
        "description": "中国-吉林-白山",
        "vendorAlias": "白山"
    }
]
```

### No Result
``` json
[]
```    