with open(r'logs.txt') as f:
    lines = f.readlines()
    
newLines=[]
additionLines=[]
subtractionLines=[]

for line in lines:
    if "(+)" in line:
        newLines.append(line)
        
        
for x in newLines:
    li = x.split(",")
    additionLines.append(li[1])
    try:
        subtractionLines.append(li[2])
    except Exception as e:
        continue
    
for x in range(len(additionLines)):
    additionLines[x] = int(additionLines[x].strip().split(" ")[0])
    
for x in range(len(subtractionLines)):
    subtractionLines[x] = int(subtractionLines[x].strip().split(" ")[0])

print(sum(additionLines),",",sum(subtractionLines))