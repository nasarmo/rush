<template>
   <v-app>
      <div>
         <v-toolbar
            dark
            prominent
            src="https://cdn.vuetifyjs.com/images/backgrounds/vbanner.jpg"
            >
            <v-toolbar-title>The Rush</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-tooltip bottom>
               <template v-slot:activator="{ on, attrs }">
                  <v-icon
                     dark
                     v-bind="attrs"
                     v-on:click="downloadCsv"
                     v-on="on"
                     >mdi-export</v-icon>
               </template>
               <span>Export CSV Data</span>
            </v-tooltip>
         </v-toolbar>
         <v-container
            class="spacing-playground pa-6"
            fluid
            >
            <v-row>
               <v-col>
                  <v-list-item>
                     <v-col cols="12" sm="6">
                        <v-text-field v-model="player"
                           label="Search for Player"
                           single-line
                           ></v-text-field>
                     </v-col>
                     <v-btn icon class="hidden-xs-only">
                        <v-icon v-on:click="searchPlayer(player)">mdi-magnify</v-icon>
                     </v-btn>
                  </v-list-item>
               </v-col>
               <v-col>
                  <v-menu>
                     <template v-slot:activator="{ on: menu, attrs }">
                        <v-tooltip bottom>
                           <template v-slot:activator="{ on: tooltip }">
                              <v-btn
                                 color="primary"
                                 dark
                                 v-bind="attrs"
                                 v-on="{ ...tooltip, ...menu }"
                                 >Filter by</v-btn>
                           </template>
                           <span>Sort by one of these</span>
                        </v-tooltip>
                     </template>
                     <v-list>
                        <v-list-item
                           v-for="(item, index) in filters"
                           :key="index"
                           @click="selectFilter(item)"
                           >
                           <v-list-item-title>{{ item }}</v-list-item-title>
                        </v-list-item>
                     </v-list>
                  </v-menu>
                     <v-menu>
                         <template v-slot:activator="{ on: menu, attrs }">
                            <v-tooltip bottom>
                               <template v-slot:activator="{ on: tooltip }">
                                  <v-btn
                                     color="red"
                                     dark
                                     v-bind="attrs"
                                     v-on="{ ...tooltip, ...menu }"
                                     >Direction</v-btn>
                               </template>
                               <span>Sort by one of these</span>
                            </v-tooltip>
                         </template>
                         <v-list>
                            <v-list-item
                               v-for="(item, index) in direction"
                               :key="index"
                               @click="selectDirection(item)"
                               >
                               <v-list-item-title>{{ item }}</v-list-item-title>
                            </v-list-item>
                         </v-list>
                      </v-menu>
                  <v-col cols="12">
                     <v-text-field
                        v-model="selectedFilter"
                        outlined
                        label="Selected Filter"
                        type="text"
                        >
                        <template v-slot:prepend>
                        </template>
                     </v-text-field>
                  </v-col>
               </v-col>
            </v-row>
         </v-container
            >
         <v-menu
            v-for="([text, rounded], index) in btns"
            :key="text"
            :rounded="rounded"
            offset-y
            >
            <template v-slot:activator="{ attrs, on }">
               <v-btn
                  :color="colors[index]"
                  class="white--text ma-8"
                  v-bind="attrs"
                  v-on="on"
                  >
                  {{ text }} Radius
               </v-btn>
            </template>
            <v-list>
               <v-list-item
                  v-for="item in items"
                  :key="item"
                  link
                  >
                  <v-list-item-title v-text="item"></v-list-item-title>
               </v-list-item>
            </v-list>
         </v-menu>
         <v-data-table :dark="true"
            :headers="headers"
            :items="players"
            :options.sync="options"
            :items-per-page="5"
            hide-default-footer
            :loading="loading"
            class="elevation-1"
            ></v-data-table>
      </div>
      <v-pagination
         v-model="currentPage"
         :length="sizeOfPlayers"
         :total-visible="7"
         @input="selectFilter()"
         ></v-pagination>
   </v-app>
</template>
<script>
   import http from "../http-common";
   export default {
     data: () => ({
       headers: [
             {text: 'Player',align: 'start', sortable: false,value: 'Player',width: "250px", fixed: true},
             { text: 'Team', value: 'Team',sortable:false },
             { text: 'Position', value: 'Pos' },
             { text: 'Total Rushing Yards', value: 'Yds' },
             { text: 'Longest Rush', value: 'Lng' },
             { text: 'Total Rushing Touchdowns', value: 'TD' },
       ],
       loading: false,
       options: {
         page: 0,
         itemsPerPage: 10,
         colors: ['deep-purple accent-4', 'error', 'teal darken-1'],
         items: [...Array(4)].map((_, i) => `Item ${i}`),
       },
       players: [],
       filters: ["NONE"],
       direction: ["ASC", "DESC"],
       selectedDirection: "DESC",
       currentPage: 1,
       sizeOfPlayers: 0,
       player: "",
       selectedFilter: 'NONE'
     }),
     mounted () {
       this.getFilters()
       this.getPlayers()

     },
     methods: {
       async getFilters () {
          this.filters =  await http.get("/api/v1/rush/getFilters").then(response => {
                                                                                                 return response.data;
                                                                                             });
       },
       async getPlayers(){
            this.loading = true;
            var rushData =   await http.get("/api/v1/rush/?player="+this.player+"&&page="+this.currentPage+"&&filter="+this.selectedFilter+"&&order="+this.selectedDirection).then(response => {
                                                                                      return response.data;
                                                                         });
            this.players = rushData.rushDtos;
            this.sizeOfPlayers = rushData.sizeOfPlayers + 1;
            this.loading = false;
       },
       selectFilter(filter) {
           if (filter){
                 this.selectedFilter = filter;
           }
           this.getPlayers();
       },
       selectDirection(direction) {
           if (direction){
                this.selectedDirection = direction;
           }
           this.getPlayers();
       },
       searchPlayer (name) {
          this.player = name;
          this.getPlayers();
       },
       async downloadCsv () {
           var currentSet =   await http.get("/api/v1/rush/getCsv/?filter="+this.selectedFilter+"&&player="+this.player).then(response => {
                                                                  return response.data;
                                                              });
           const url = window.URL.createObjectURL(new Blob([currentSet]));
           const link = document.createElement('a');
           link.href = url;
           link.setAttribute('download', 'rushing.csv');
           document.body.appendChild(link);
           link.click();
       }
     },
   }
</script>