
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
          <v-btn icon>
            <v-icon  v-on:click="downloadCsv">mdi-export</v-icon>
          </v-btn>
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
      :loading="loading"
      class="elevation-1"
    ></v-data-table>
  </div>
</v-app>

</template>

<script>
import http from "../http-common";
export default {
  data: () => ({
    headers: [
          {text: 'Player',align: 'start', sortable: false,value: 'Player',},
          { text: 'Team', value: 'Team',sortable:false },
          { text: 'Position', value: 'Pos' },
          { text: 'Total Rushing Yards', value: 'Yds' },
          { text: 'Longest Rush', value: 'Lng' },
          { text: 'Total Rushing Touchdowns', value: 'TD' },
    ],
    loading: false,
    options: {
      page: 1,
      itemsPerPage: 10,
      sortBy: ['name'],
      sortDesc: [true],
      btns: [
        ['Sort By', '0'],
      ],
      colors: ['deep-purple accent-4', 'error', 'teal darken-1'],
      items: [...Array(4)].map((_, i) => `Item ${i}`),
    },
    players: [],
    filters: [],
    selectedFilter: 'No filter'
  }),
  mounted () {
    this.getFilters()
    this.getPlayers()
    this.$vuetify.theme.dark = true;

  },
  methods: {
    async getPlayers () {
       this.loading = true
       this.players =   await http.post("/loadFromJson").then(response => {
                                                               return response.data;
                                                           });
       this.loading = false;
    },
    async getFilters () {
       this.filters =  await http.get("/getFilters").then(response => {
                                                                                              return response.data;
                                                                                          });
    },
    async selectFilter(filter) {
        this.loading = true;
        this.players =   await http.get("/"+filter).then(response => {
                                                                       return response.data;
                                                                   });
        this.selectedFilter = filter;
        this.loading = false;
    },
    async searchPlayer (name) {
       this.loading = true
       if (name == ""){
              this.players =   await http.post("/loadFromJson").then(response => {
                                                                      return response.data;
                                                                  });
       } else {
               this.players =   await http.get("/getPlayer?player="+name).then(response => {
                                                               return response.data;
                                                           });
       }
       this.loading = false;
    },
    async downloadCsv () {
       this.players =   await http.post("/getCsv", this.players).then(response => {
                                                               return response.data;
                                                           });
        const url = window.URL.createObjectURL(new Blob([this.players]));
        const link = document.createElement('a');
        link.href = url;
        link.setAttribute('download', 'file.csv');
        document.body.appendChild(link);
        link.click();
    }
  },
}
</script>