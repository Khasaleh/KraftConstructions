# base image
FROM node:12-alpine

# set working directory
WORKDIR /app

# copy project files and install dependencies
COPY . /app
RUN npm install

# expose port
EXPOSE 4200

# start the app
CMD ["npm", "start"]
