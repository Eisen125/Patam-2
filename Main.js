
/*var dates = [
    new Ad({
        "name": "one",
       " templateUrl": "templates/Template1.html",
        "texts": ["text1", "text2", "text3", "text4"],
        "imagesUrl": ["images/img1.jpg", "images/img2.jpg"],
        "days": {
           " monday": {
               " fromHour": 6,
               " toHour": 12
            },
           " wednesday": {
               " fromHour": 13,
               " toHour": 20
            }
        },

         
        year1:2022,
       " fromDate": "1/1/2022",
        "toDate": "12/31/2022",
        "timeDuration": 3,
       " screens": { "1": true, "2": true }
    }),
    new Ad({
        "name": "two",
       " templateUrl": "templates/Template2.html",
        "texts": ["text1", "text2", "text3", "text4", "text5", "text6", "text7", "text8", "text9", "text10"],
        "imagesUrl": ["images/img1.jpg"],
        "days": {
          "  tuesday": {
               " fromHour": 10,
               " toHour": 16
            },
           " wednesday": {
               " fromHour": 10,
               " toHour": 16
            }
        },
        
        year2:2022,
       " fromDate": "3/1/2022",
        "toDate": "4/31/2022",
        "timeDuration": 5,
       " screens": { "1": true, "3": true }
    }),
    new Ad({
        "name": "three",
       " templateUrl": "templates/Template3.html",
        "texts": [],
        "imagesUrl": [],
        "days": {
           "": true,
           "fromHour": 8,
           "toHour": 22
        },
        
        year3:2022,
       " fromDate": "5/1/2022",
        "toDate": "6/15/2022",
        "timeDuration": 7,
       " screens": { "2": true, "3": true }
    }),
    new Ad({
        "name": "four",
       " templateUrl": "templates/Template1.html",
        "texts": ["text1", "text2"],
        "imagesUrl": [],
        "days": {
           " monday": {
               " fromHour": 6,
               " toHour": 12
            }
        },
        year4:2022,
       " fromDate": "3/29/2022",
        "toDate": "4/15/2022",
        "timeDuration": 4,
       " screens": { "1": true }
    }),
    new Ad({
        "name": "five",
       " templateUrl": "templates/Template2.html",
        "texts": ["text1", "text2", "text3", "text4", "text5", "text6", "text7"],
        "imagesUrl": ["images/img1.jpg", "images/img2.jpg"],
        "days": {
           " monday": {
               " fromHour": 1,
               " toHour": 23,
            }, tuesday: {
               " fromHour": 1,
               " toHour": 23,
            }," wednesday": {
               " fromHour": 1,
               " toHour": 23,
            }
        },
      
        year5:2022,
       " fromDate": "4/1/2022",
        "toDate": "4/31/2022",
        "timeDuration": 6,
       " screens": { "3": true }
    })
    
];

 
var myDate= new Date();
year = myDate.getFullYear();
month = myDate.getMonth()+1;
hours = myDate.getHours();
"days" = myDate.getDate();
date=myDate.getDate();
var Years=[year1,year2,year3,year4,year5]
var "days" =[]
var months=[]
var hours=[]
for(var i=0;i<dates.length;i++)
{
    if (year==Years[i])
    


}


*/
const currentDate = new Date()
const dayName =  currentDate.toLocaleDateString('en-us', { weekday: 'long' })

function showAds() {
    var ads = fakeData()
    ads.forEach(ad => {
        const fromDate = dateStringToDate(ad.fromDate)
        const toDate = dateStringToDate(ad.toDate)
        if(checkDay(fromDate, toDate)){
            const days = ad.days
            const day = days[dayName]
            if(day){
                if(checkHours(day)){
                   
                    console.log(ad.name)
                    console.log(ad.texts)
                    console.log(ad.templateUrl)
                   
                }
            }
        }
    });
}

function fakeData() {
    var ads = []
    var ad1 = {
        "name": "one",
        "templateUrl": "templates/Template1.html",
        "texts": ["text1", "text2", "text3", "text4"],
        "imagesUrl": ["images/crash.png","images/kick.png"],
        "days": {
           "Monday": {
               "fromHour": 6,
               "toHour": 12
            },
           "Wednesday": {
               "fromHour": 13,
               "toHour": 20
            }
        },
       "fromDate": "1/1/2022",
        "toDate": "12/31/2022",
        "timeDuration": 3,
       "screens": { "1": true, "2": true }
    }
   var ad2={
        "name": "two",
       "templateUrl": "templates/Template2.html",
        "texts": ["text1", "text2", "text3", "text4", "text5", "text6", "text7", "text8", "text9", "text10"],
        "imagesUrl": ["images/snare.png"],
        "days": {
          "Tuesday": {
               "fromHour": 10,
               "toHour": 17
            },
           "Wednesday": {
               "fromHour": 10,
               "toHour": 16
            }
        },
       "fromDate": "3/1/2022",
        "toDate": "4/31/2022",
        "timeDuration": 5,
       "screens": { "1": true, "3": true }
    } 
    
   var ad3={
        "name": "three",
       "templateUrl": "templates/Template3.html",
        "texts": [],
        "imagesUrl": [],
        "days": {
            "Monday": {
                "fromHour": 6,
                "toHour": 12
             }
        },
       "fromDate": "5/1/2022",
        "toDate": "6/15/2022",
        "timeDuration": 7,
       "screens": { "2": true, "3": true }
    }

    var ad4={
        "name": "four",
       "templateUrl": "templates/Template1.html",
        "texts": ["text1", "text2"],
        "imagesUrl": [],
        "days": {
           "Monday": {
               "fromHour": 6,
               "toHour": 12
            }
        },
       "fromDate": "3/29/2022",
        "toDate": "4/15/2022",
        "timeDuration": 4,
       "screens": { "1": true }
    }
    var ad5 = {
        "name": "five",
       "templateUrl": "templates/Template2.html",
        "texts": ["text1", "text2", "text3", "text4", "text5", "text6", "text7"],
        "imagesUrl": ["images/tom1.png", "images/tom2.png"],
        "days": {
           "Monday": {
               "fromHour": 1,
               "toHour": 23,
            }, "Tuesday": {
               "fromHour": 1,
               "toHour": 23,
            },"Wednesday": {
               "fromHour": 1,
               "toHour": 23,
            }
        },   
       "fromDate": "4/1/2022",
        "toDate": "4/31/2022",
        "timeDuration": 6,
       "screens": { "3": true }
    }

    ads.push(ad1)
    ads.push(ad2)
    ads.push(ad3)
    ads.push(ad4)
    ads.push(ad5)
    return ads
}

function dateStringToDate(dateString) {
    return new Date(dateString)
}
function checkDay(from, to) {
    return (currentDate.getTime() <= to.getTime() && currentDate.getTime() >= from.getTime())
}
function checkHours(day) {
    return (currentDate.getHours() <= day.toHour && currentDate.getHours() >= day.fromHour)
}
showAds()

$("Templates").load(ads.templateUrl);