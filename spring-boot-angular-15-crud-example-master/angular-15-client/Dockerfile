# base image
FROM node:16.18-alpine3.15

# set working directory
WORKDIR /app

# copy project files and install dependencies
COPY . /app
RUN npm install
RUN npm i -g @angular/cli


# expose port
EXPOSE 4200

# start the app
CMD ["npm", "start"]
