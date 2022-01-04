from json.decoder import JSONDecodeError
from urllib.request import urlopen, Request
from fake_useragent import UserAgent
import json
import re
import sqlite3

useragent = UserAgent()

baseUrl1 = 'https://recipe.bom.co.kr/recipe/'
baseUrl2 = '/detail'

#DB connect
conn=sqlite3.connect('step.db')
cur=conn.cursor()

for i in range(181,1026):
    print("%d번째 요리:"%i)

    url0 = baseUrl1 + str(i) + baseUrl2
    recipenum=i
    print(recipenum)
    headers = {
        'referer' : url0,
        'User-Agent' : useragent.chrome
    }
    url = 'https://recipe.bom.co.kr/api/v1/post?embeds=user%2Ccate_detail%2Cop_function%2Cop_difficulty&filters=status=1,id='+str(i)

    response = urlopen(Request(url, headers=headers)).read().decode('utf-8')

    try:
        #조리순서
        res2 = json.loads(response)['result'][0]['steps']
        found2 = re.findall('tent":"(.+?)"',res2)
        found=re.findall('\"images\":\"(.+?)\"',res2)
        j=len(found)
        for i in range(len(found2)):
            print(i+1)
            step_txt=found2[i].encode('utf-8').decode('unicode_escape')
            if j<=0:
                img_step="null"
            else:
                img_step="https://recipe.bom.co.kr/uploads/"+found[i]
            data=(recipenum,i+1,step_txt,img_step)
            cur.execute("insert into step values (?, ?, ?, ?)", data)
            conn.commit()
            j-=1



    except(IndexError):
        print("레시피가 없습니다.")
    

    


    



